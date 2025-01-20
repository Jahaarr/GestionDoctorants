package com.ensem.gestiondoctorants.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String journal;
    private String date;

    public Article() {}

    public Article(String title, String journal, String date) {
        this.title = title;
        this.journal = journal;
        this.date = date;
    }
}
