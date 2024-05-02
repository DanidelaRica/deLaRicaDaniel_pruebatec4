package com.hackaboss.PruebaTenica4Final.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigo;
    private String nombre;
    private String ciudad;

    @OneToMany(mappedBy= "hotel", cascade = CascadeType.ALL)
    @JsonIgnore // Evita la serialización recursiva
    private Set<Habitacion> habitaciones; //Usamos Set para que cada habitacion sea unica

    public Hotel(Long id, String codigo, String nombre, String ciudad) {//usamos este constructor para la prueba unitaria
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }
}
