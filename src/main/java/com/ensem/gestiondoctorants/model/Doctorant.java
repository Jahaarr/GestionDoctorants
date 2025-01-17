package com.ensem.gestiondoctorants.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doctorant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String cne;
    private String cin;
    private String formationDoctorale;
    private String titreThese;
    private String directeurThese;
}
