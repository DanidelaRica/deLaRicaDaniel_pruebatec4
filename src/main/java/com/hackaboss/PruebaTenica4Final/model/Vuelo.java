package com.hackaboss.PruebaTenica4Final.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroVuelo;
    private String origen;
    private String destino;
    private String tipoAsiento;
    private double precioPorPersona;
    private LocalDate fechaIda;

    private int asientosDisponibles;

    private boolean reservado;

    @OneToMany(mappedBy = "vuelo")
    private List<Reserva> reservas;
}
