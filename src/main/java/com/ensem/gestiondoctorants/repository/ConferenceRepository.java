package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {}
