package com.frb.books.classes;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("publisher")
    private String publisher;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
