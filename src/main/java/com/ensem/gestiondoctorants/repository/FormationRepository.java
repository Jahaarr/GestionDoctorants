package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {}
