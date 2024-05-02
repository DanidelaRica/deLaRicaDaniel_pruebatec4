package com.hackaboss.PruebaTenica4Final.controller;


import com.hackaboss.PruebaTenica4Final.model.Vuelo;
import com.hackaboss.PruebaTenica4Final.service.IVueloService;
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
import java.util.Map;

@RestController
@RequestMapping("/agency/flights")
public class VueloController {
    @Autowired
    private IVueloService vueloService;

    @GetMapping
    public List<Vuelo> getAllVuelos() {
        return vueloService.getAllVuelos();
    }

    @GetMapping("/{id}")
    public Vuelo getVueloById(@PathVariable Long id) {
        return vueloService.getVueloById(id);
    }

    @PostMapping
    public Vuelo createVuelo(@RequestBody Vuelo vuelo) {
        return vueloService.createVuelo(vuelo);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró el vuelo que intenta editar")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Vuelo> updateVuelo(@PathVariable Long id, @RequestBody Vuelo vuelo) {
        try {
            Vuelo updatedVuelo = vueloService.updateVuelo(id, vuelo);
            if (updatedVuelo != null) {
                return ResponseEntity.ok(updatedVuelo);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVuelo(@PathVariable Long id) {
        try {
            vueloService.deleteVuelo(id);
            return ResponseEntity.ok("Vuelo borrado exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo borrar el vuelo");
        }
    }


    @GetMapping("/availableFlights")
    public List<Vuelo> getAvailableFlights(
            @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
            @RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo,
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination) {
        return vueloService.getAvailableFlights(dateFrom, dateTo, origin, destination);
    }

    @PostMapping("/new")
    public double bookFlight(@RequestBody Map<String, Object> reservaRequest) {
        // Extraer los datos del mapa
        int cantidadPersonas = (int) reservaRequest.get("cantidadPersonas");
        String origen = (String) reservaRequest.get("origen");
        String destino = (String) reservaRequest.get("destino");
        LocalDate fechaIda = LocalDate.parse((String) reservaRequest.get("fechaIda"));

        // Llamar al servicio para reservar el vuelo
        return vueloService.bookFlight(cantidadPersonas, origen, destino, fechaIda);
    }
}
