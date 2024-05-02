package com.hackaboss.PruebaTenica4Final.service;

import com.hackaboss.PruebaTenica4Final.model.Persona;

import java.util.List;

public interface IPersonaService {
    public List<Persona> getAllPeople();

    public Persona getPersonaById(Long id);

    public Persona createPersona(Persona persona);

    public Persona updatePersona(Long id, Persona persona);

    public void deletePersona(Long id);
}
