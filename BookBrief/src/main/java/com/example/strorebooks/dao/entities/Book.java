package com.example.strorebooks.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString

public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String author;

    private String genre;

    @Column(columnDefinition = "TEXT")
    private String summary;

    public Book(int id, String name, String genre, String summary) {
        super();
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.summary = summary;
    }
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }


}