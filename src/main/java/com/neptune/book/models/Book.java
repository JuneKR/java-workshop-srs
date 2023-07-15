package com.neptune.book.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Book {
    private final String id;
    private String title;
    private String author;
    private String genre;

    public Book(String id) {
        this.id = id;
    }

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book( @JsonProperty("id") String id,
                 @JsonProperty("title") String title,
                 @JsonProperty("author") String author,
                 @JsonProperty("genre") String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, String author, String genre) {
        this(UUID.randomUUID().toString(), title, author, genre);
    }

    public String getId() {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
