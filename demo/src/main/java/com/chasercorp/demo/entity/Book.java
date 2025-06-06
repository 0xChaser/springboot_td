package com.chasercorp.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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