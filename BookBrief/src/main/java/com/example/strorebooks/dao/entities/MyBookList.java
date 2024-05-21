package com.example.strorebooks.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="MyBooks")
public class MyBookList {

    @Id
    private int id;
    private String name;
    private String author;
    private String genre;

    @Column(columnDefinition = "TEXT")
    private String summary;

    public MyBookList() {
        super();
        // TODO Auto-generated constructor stub
    }
    public MyBookList(int id, String name, String author, String genre, String summary) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.summary = summary;
    }

}