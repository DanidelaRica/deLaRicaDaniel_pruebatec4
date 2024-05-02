package com.hackaboss.PruebaTenica4Final.service;


import com.hackaboss.PruebaTenica4Final.model.Habitacion;
import com.hackaboss.PruebaTenica4Final.model.Reserva;
import com.hackaboss.PruebaTenica4Final.repository.HabitacionRepository;
import com.hackaboss.PruebaTenica4Final.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitacionService implements IHabitacionService{

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Habitacion> getAllHabitaciones() {
        return habitacionRepository.findAll();
    }

    @Override
    public Habitacion getHabitacionById(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habitación with id " + id + " not found"));
    }

    @Override
    public Habitacion createHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public Habitacion updateHabitacion(Long id, Habitacion habitacion) {
        if (!habitacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Habitación with id " + id + " not found");
        }
        habitacion.setId(id);
        return habitacionRepository.save(habitacion);
    }

    @Override
    public void deleteHabitacion(Long id) {
        if (!habitacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Habitación with id " + id + " not found");
        }
        habitacionRepository.deleteById(id);
    }

    @Override
    public List<Habitacion> getAvailableRooms(LocalDate dateFrom, LocalDate dateTo, String destination) {
        // Obtenemos todas las habitaciones disponibles en el destino seleccionado
        List<Habitacion> allRoomsInDestination = habitacionRepository.findByCiudad(destination);

        // Filtramos las habitaciones que están disponibles en el rango de fechas especificado usando streams y lambdas
        List<Habitacion> availableRooms = allRoomsInDestination.stream()
                .filter(habitacion -> isRoomAvailableInDateRange(habitacion, dateFrom, dateTo))
                .collect(Collectors.toList());

        return availableRooms;
    }
    @Override
    public boolean isRoomAvailableInDateRange(Habitacion habitacion, LocalDate dateFrom, LocalDate dateTo) {
        // Obtenemos las reservas de la habitación en el rango de fechas especificado
        List<Reserva> reservas = reservaRepository.findByHabitacionAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(habitacion, dateTo, dateFrom);

        // Verificamos si hay reservas en el rango de fechas especificado
        return reservas.isEmpty();
    }
    @Override
    public Habitacion getHabitacionByCodigoAndTipo(String codigo, String tipo) {
        return habitacionRepository.findByCodigoAndTipo(codigo, tipo);
    }



}
