package com.hackaboss.PruebaTenica4Final.repository;

import com.hackaboss.PruebaTenica4Final.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
