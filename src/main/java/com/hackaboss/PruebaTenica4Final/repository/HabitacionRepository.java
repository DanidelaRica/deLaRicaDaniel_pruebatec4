package com.hackaboss.PruebaTenica4Final.repository;


import com.hackaboss.PruebaTenica4Final.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByCiudad(String ciudad);
    Habitacion findByCodigoAndTipo(String codigo, String tipo);


}
