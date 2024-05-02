package com.hackaboss.PruebaTenica4Final.service;


import com.hackaboss.PruebaTenica4Final.exception.FechasInvalidasException;
import com.hackaboss.PruebaTenica4Final.exception.HabitacionNoDisponibleException;
import com.hackaboss.PruebaTenica4Final.exception.ReservaExistenteException;
import com.hackaboss.PruebaTenica4Final.model.Habitacion;
import com.hackaboss.PruebaTenica4Final.model.Reserva;
import com.hackaboss.PruebaTenica4Final.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaService implements IReservaService{
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionService habitacionService; // Inyecta HabitacionService

    @Override
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva with id " + id + " not found"));
    }

    @Override
    public Reserva createReserva(Reserva reserva) {
        // Verificar si ya existe una reserva con las mismas características
        if (reservaRepository.existsByFechaInicioAndFechaFinAndNombreClienteAndHabitacion(
                reserva.getFechaInicio(), reserva.getFechaFin(), reserva.getNombreCliente(), reserva.getHabitacion())) {
            throw new ReservaExistenteException("Ya existe una reserva con las mismas características.");
        }

        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva updateReserva(Long id, Reserva reserva) {
        if (!reservaRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva with id " + id + " not found");
        }
        reserva.setId(id);
        return reservaRepository.save(reserva);
    }

    @Override
    public void deleteReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva with id " + id + " not found");
        }
        reservaRepository.deleteById(id);
    }

    @Override
    public double bookRoom(Reserva reserva) {
        // Obtener la habitación basada en el código del hotel y el tipo de habitación
        Habitacion habitacion = habitacionService.getHabitacionByCodigoAndTipo(reserva.getCodigoHotel(), reserva.getTipoHabitacion());

        if (habitacion == null) {
            // La habitación no está disponible
            throw new HabitacionNoDisponibleException("La habitación no está disponible.");
        }

        // Obtener el precio por noche de la habitación
        double precioPorNoche = habitacion.getPrecioPorNoche();

        // Verificar disponibilidad de fechas
        LocalDate fechaInicio = reserva.getFechaInicio();
        LocalDate fechaFin = reserva.getFechaFin();
        long numNoches = ChronoUnit.DAYS.between(fechaInicio, fechaFin);

        if (numNoches <= 0) {
            // Fechas inválidas
            throw new FechasInvalidasException("Las fechas de reserva son inválidas.");
        }

        if (!habitacionService.isRoomAvailableInDateRange(habitacion, fechaInicio, fechaFin)) {
            // La habitación no está disponible en las fechas especificadas
            throw new HabitacionNoDisponibleException("La habitación no está disponible en las fechas especificadas.");
        }

        // Calcular el precio total de la reserva
        double totalReserva = precioPorNoche * numNoches;

        // Realizar la reserva en la base de datos o en el sistema de reservas externo
        // (aquí puedes realizar la lógica de guardar la reserva en la base de datos, enviar confirmaciones por correo electrónico, etc.)

        return totalReserva;
    }

}

