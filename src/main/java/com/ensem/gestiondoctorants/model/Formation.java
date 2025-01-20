package com.ensem.gestiondoctorants.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String formation;
    private String date;
    private String location;

    public Formation(String formation, String date, String location) {
        this.formation = formation;
        this.date = date;
        this.location = location;
    }

    public Formation() {}
}
