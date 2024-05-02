package com.hackaboss.PruebaTenica4Final.controller;

import com.hackaboss.PruebaTenica4Final.model.Persona;
import com.hackaboss.PruebaTenica4Final.service.IPersonaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/personas")
public class PersonaController {
    @Autowired
    private IPersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id) {
        return personaService.getPersonaById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La persona se creó correctamente"),
            @ApiResponse(responseCode = "409", description = "La persona ya existe")
    })
    @PostMapping("/new")
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona createdPersona = personaService.createPersona(persona);
        if (createdPersona == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPersona);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró la persona que intenta editar")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        Persona updatedPersona = personaService.updatePersona(id, persona);
        if (updatedPersona == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(updatedPersona);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable Long id) {
        try {
            personaService.deletePersona(id);
            return ResponseEntity.ok("Persona eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la persona");
        }
    }


}
