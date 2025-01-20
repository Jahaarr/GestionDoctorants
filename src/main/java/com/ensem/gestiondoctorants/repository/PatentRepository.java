package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Patent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentRepository extends JpaRepository<Patent, Long> {}
