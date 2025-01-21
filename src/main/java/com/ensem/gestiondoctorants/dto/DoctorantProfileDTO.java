package com.ensem.gestiondoctorants.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DoctorantProfileDTO {
    private String cne;
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
    private Date dateNaissance;
}