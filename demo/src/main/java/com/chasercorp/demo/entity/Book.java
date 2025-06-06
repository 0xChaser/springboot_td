package com.chasercorp.demo.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private String id;

    private String title;
    private String isbn;

    @ManyToOne
    private Author author;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title; 
    }

    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) { 
        this.isbn = isbn; 
    }

    public Author getAuthor() { 
        return author;
    }

    public void setAuthor(Author author) { 
        this.author = author;
    }
} 