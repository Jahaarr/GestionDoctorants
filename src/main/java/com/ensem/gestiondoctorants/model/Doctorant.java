package com.ensem.gestiondoctorants.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doctorant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cne;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String numTel;

    // Newly added fields
    private String codeApogee;
    private String sexe;
    private String villeNaissance;
    private String nomArab;
    private String prenomArab;
    private String villeNaissanceArab;
    private String anneeUniv;
    private String codEtp;
    private String codVrsVet;
    private String codDip;
    private String codVrsVdi;
    private Integer nbrInsCyc;
    private Integer nbrInsDip;
    private String mentionBac;
    private String anneeBac;
    private String libDip;
    private String libDipArb;
    private String formationDoctorale;
    private String titreThese;
    private String directeurThese;
    private String codirecteurThese;
    private Boolean cotutelle;
    private Boolean bourse;

    @Temporal(TemporalType.DATE)
    private java.util.Date dateNaissance;
    @Column(nullable = false, length = 20)
    private String role = "DOCTORANT"; // Default role

}
