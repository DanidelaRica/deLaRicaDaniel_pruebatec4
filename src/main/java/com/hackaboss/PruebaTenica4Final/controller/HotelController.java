package com.hackaboss.PruebaTenica4Final.controller;


import com.hackaboss.PruebaTenica4Final.exception.ConflictException;
import com.hackaboss.PruebaTenica4Final.exception.InvalidRequestException;
import com.hackaboss.PruebaTenica4Final.exception.UnauthorizedException;
import com.hackaboss.PruebaTenica4Final.model.Hotel;
import com.hackaboss.PruebaTenica4Final.service.IHotelService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/hotels")
public class HotelController {
    @Autowired
    private IHotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHoteles() {
        return hotelService.getAllHoteles();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "401", description = "No autorizado para crear el hotel"),
            @ApiResponse(responseCode = "409", description = "El hotel ya existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/new")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        try {
            Hotel createdHotel = hotelService.createHotel(hotel);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró el hotel que intenta editar")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        if (updatedHotel != null) {
            return ResponseEntity.ok(updatedHotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel eliminado exitosamente");
    }
}
