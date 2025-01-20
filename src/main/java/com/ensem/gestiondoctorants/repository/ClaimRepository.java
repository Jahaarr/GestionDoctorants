package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
