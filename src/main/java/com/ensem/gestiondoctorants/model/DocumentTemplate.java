package com.ensem.gestiondoctorants.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "document_templates")
public class DocumentTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String type;  // Document type (e.g., "Attestation d'inscription")

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;  // Template content
}