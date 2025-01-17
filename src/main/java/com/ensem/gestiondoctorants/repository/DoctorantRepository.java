package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Doctorant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorantRepository extends JpaRepository<Doctorant, Long> {
}
