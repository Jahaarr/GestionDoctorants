package com.ensem.gestiondoctorants.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DocumentTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String documentType; // e.g., "Attestation", "Carte", etc.

    @Lob // Allows storing large text content
    private String templateContent; // Content of the document template
}
