package com.ensem.gestiondoctorants.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conference;
    private String date;
    private String location;

    public Conference(String conference, String date, String location) {
        this.conference = conference;
        this.date = date;
        this.location = location;
    }

    public Conference() {}
}
