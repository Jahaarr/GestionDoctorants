package com.ensem.gestiondoctorants.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Patent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String date;
    private String description;

    public Patent() {}

    public Patent(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }
}
