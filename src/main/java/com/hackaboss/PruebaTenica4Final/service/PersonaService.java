package com.hackaboss.PruebaTenica4Final.service;

import com.hackaboss.PruebaTenica4Final.model.Persona;
import com.hackaboss.PruebaTenica4Final.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonaService implements IPersonaService{
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getAllPeople() {
        return personaRepository.findAll();
    }

    @Override
    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona with id " + id + " not found"));
    }

    @Override
    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Persona updatePersona(Long id, Persona persona) {
        if (!personaRepository.existsById(id)) {
            throw new EntityNotFoundException("Persona with id " + id + " not found");
        }
        persona.setId(id);
        return personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new EntityNotFoundException("Persona with id " + id + " not found");
        }
        personaRepository.deleteById(id);
    }
}
