package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String title;
    private String author;
    private boolean borrowed;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User borrowedBy;
    public Book() {}
    public Book(String title, String author, boolean borrowed, User borrowedBy) {
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
        this.borrowedBy = borrowedBy;
    }
    public Book(Long id, String title, String author, boolean borrowed, User borrowedBy) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
        this.borrowedBy = borrowedBy;
    }


    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
}
