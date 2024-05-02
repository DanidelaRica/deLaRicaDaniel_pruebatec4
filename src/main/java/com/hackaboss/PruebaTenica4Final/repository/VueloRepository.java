package com.hackaboss.PruebaTenica4Final.repository;


import com.hackaboss.PruebaTenica4Final.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByOrigenAndDestinoAndFechaIdaBetween(String origen, String destino, LocalDate fechaIdaDesde, LocalDate fechaIdaHasta);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen AND v.destino = :destino AND v.fechaIda = :fechaIda")
    List<Vuelo> findAvailableFlights(@Param("origen") String origen, @Param("destino") String destino, @Param("fechaIda") LocalDate fechaIda);
}
