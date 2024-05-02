package com.hackaboss.PruebaTenica4Final.controller;


import com.hackaboss.PruebaTenica4Final.exception.InvalidRequestException;
import com.hackaboss.PruebaTenica4Final.model.Reserva;
import com.hackaboss.PruebaTenica4Final.service.IReservaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/reservas")
public class ReservaController {
    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.getAllReservas();
    }

    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud de reserva inválida")
    })
    @PostMapping("/new")
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        try {
            Reserva createdReserva = reservaService.createReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReserva);
        } catch (InvalidRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró la reserva que intenta editar")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            Reserva updatedReserva = reservaService.updateReserva(id, reserva);
            if (updatedReserva != null) {
                return ResponseEntity.ok(updatedReserva);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable Long id) {
        try {
            reservaService.deleteReserva(id);
            return ResponseEntity.ok("Reserva eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La reserva no se pudo eliminar");
        }
    }

}
