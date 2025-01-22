package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Doctorant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorantRepository extends JpaRepository<Doctorant, Long> {
    Doctorant findByCne(String cne);
    Doctorant findByCin(String cin);
    Doctorant findByEmail(String email);

}
