package com.ensem.gestiondoctorants.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Doctorant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cne;

    @Column(nullable = false)
    private String password;

    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String numTel;
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

    @Column(nullable = false)
    private java.time.LocalDate dateNaissance;

    @Column(nullable = false, length = 20)
    private String role = "DOCTORANT";

    @Column(nullable = false)
    private boolean firstLogin = true;  // New field for first login check

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}