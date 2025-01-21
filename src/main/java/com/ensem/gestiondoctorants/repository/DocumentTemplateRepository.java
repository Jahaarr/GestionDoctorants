package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.DocumentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTemplateRepository extends JpaRepository<DocumentTemplate, Long> {
    Optional<DocumentTemplate> findByDocumentType(String documentType);
}
