package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Doctorant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorantRepository extends JpaRepository<Doctorant, Long> {
    Optional<Doctorant> findByCne(String cne);
}
