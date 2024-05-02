package com.hackaboss.PruebaTenica4Final.controller;


import com.hackaboss.PruebaTenica4Final.model.Habitacion;
import com.hackaboss.PruebaTenica4Final.model.Reserva;
import com.hackaboss.PruebaTenica4Final.service.IHabitacionService;
import com.hackaboss.PruebaTenica4Final.service.IReservaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agency/rooms")
public class HabitacionController {
    @Autowired
    private IHabitacionService habitacionService;

    @Autowired
    private IReservaService reservaService;

    @PostMapping("/room-booking/new")
    public double bookRoom(@RequestBody Reserva reserva) {
        return reservaService.bookRoom(reserva);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones disponibles encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron habitaciones disponibles para las fechas y destino especificados")
    })
    @GetMapping("/available")
    public ResponseEntity<List<Habitacion>> getAvailableRooms(
            @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
            @RequestParam("dateTo") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo,
            @RequestParam("destination") String destination) {

        List<Habitacion> availableRooms = habitacionService.getAvailableRooms(dateFrom, dateTo, destination);
        if (availableRooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(availableRooms);
        }
    }


    @GetMapping
    public List<Habitacion> getAllHabitaciones() {
        return habitacionService.getAllHabitaciones();
    }

    @GetMapping("/{id}")
    public Habitacion getHabitacionById(@PathVariable Long id) {
        return habitacionService.getHabitacionById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La habitación se creó correctamente"),
            @ApiResponse(responseCode = "409", description = "La habitación ya existe")
    })
    @PostMapping
    public ResponseEntity<Habitacion> createHabitacion(@RequestBody Habitacion habitacion) {
        Habitacion createdHabitacion = habitacionService.createHabitacion(habitacion);
        if (createdHabitacion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.ok(createdHabitacion);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró la habitación que intenta editar")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Habitacion> updateHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        Habitacion updatedHabitacion = habitacionService.updateHabitacion(id, habitacion);
        if (updatedHabitacion == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(updatedHabitacion);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabitacion(@PathVariable Long id) {
        try {
            habitacionService.deleteHabitacion(id);
            return ResponseEntity.ok("Habitación borrada exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo borrar la habitación");
        }
    }



}
