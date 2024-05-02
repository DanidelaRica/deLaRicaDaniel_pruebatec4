package com.hackaboss.PruebaTenica4Final.service;


import com.hackaboss.PruebaTenica4Final.exception.ConflictException;
import com.hackaboss.PruebaTenica4Final.exception.VueloNoDisponibleException;
import com.hackaboss.PruebaTenica4Final.model.Vuelo;
import com.hackaboss.PruebaTenica4Final.repository.VueloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VueloService implements IVueloService{
    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public Vuelo getVueloById(Long id) {
        return vueloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight with id " + id + " not found"));
    }

    @Override
    public Vuelo createVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Vuelo updateVuelo(Long id, Vuelo vuelo) {
        if (!vueloRepository.existsById(id)) {
            throw new EntityNotFoundException("Flight with id " + id + " not found");
        }
        vuelo.setId(id);
        return vueloRepository.save(vuelo);
    }

    @Override
    public void deleteVuelo(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight with id " + id + " not found"));

        if (vuelo.isReservado()) {
            throw new ConflictException("The flight cannot be deleted because it is currently reserved.");
        }

        vueloRepository.deleteById(id);
    }

    @Override
    public List<Vuelo> getAvailableFlights(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        if (dateTo == null) {
            // Si no se especifica una fecha de vuelta, buscar vuelos de ida y vuelta en la misma fecha de vuelta
            dateTo = dateFrom;
        }

        // Buscar vuelos de ida para la fecha de ida especificada
        return vueloRepository.findByOrigenAndDestinoAndFechaIdaBetween(
                origin, destination, dateFrom, dateTo
        );
    }

    @Override
    public double bookFlight(int cantidadPersonas, String origen, String destino, LocalDate fechaIda) {
        // Obtener los vuelos disponibles para la fecha de ida, origen y destino
        List<Vuelo> vuelosDisponibles = vueloRepository.findAvailableFlights(origen, destino, fechaIda);

        // Verificar si hay suficientes asientos disponibles en alguno de los vuelos encontrados
        Vuelo vueloSeleccionado = null;
        for (Vuelo vuelo : vuelosDisponibles) {
            if (vuelo.getAsientosDisponibles() >= cantidadPersonas) {
                vueloSeleccionado = vuelo;
                break;
            }
        }

        if (vueloSeleccionado == null) {
            // No hay vuelos disponibles con suficientes asientos
            throw new VueloNoDisponibleException("No hay vuelos disponibles con suficientes asientos para la fecha y destino especificados.");
        }

        // Calcular el precio total de la reserva
        double precioTotal = vueloSeleccionado.getPrecioPorPersona() * cantidadPersonas;

        // Marcar los asientos como reservados
        vueloSeleccionado.setAsientosDisponibles(vueloSeleccionado.getAsientosDisponibles() - cantidadPersonas);
        vueloRepository.save(vueloSeleccionado);

        return precioTotal;
    }
}
