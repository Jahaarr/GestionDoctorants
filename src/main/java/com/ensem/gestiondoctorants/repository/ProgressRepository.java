package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
