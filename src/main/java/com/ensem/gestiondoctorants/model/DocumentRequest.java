package com.ensem.gestiondoctorants.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DocumentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cne; // Linked to Doctorant
    private String documentType;
    private String justification; // For exceptional requests
    private String attachmentPath; // File location on server
    private Boolean isExceptional;
}
