package com.hackaboss.PruebaTenica4Final.repository;


import com.hackaboss.PruebaTenica4Final.model.Habitacion;
import com.hackaboss.PruebaTenica4Final.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByHabitacionAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
            Habitacion habitacion, LocalDate fechaFin, LocalDate fechaInicio);

    boolean existsByFechaInicioAndFechaFinAndNombreClienteAndHabitacion(
            LocalDate fechaInicio, LocalDate fechaFin, String nombreCliente, Habitacion habitacion);

    boolean existsByHabitacion_Hotel_Id(Long hotelId);

}
