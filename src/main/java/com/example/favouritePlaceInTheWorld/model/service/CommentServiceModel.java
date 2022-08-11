package com.example.favouritePlaceInTheWorld.model.service;

public class CommentServiceModel {

    private Long placeId;
    private String textContent;
    private String author;

    public CommentServiceModel() {
    }

    public Long getPlaceId() {
        return placeId;
    }

    public CommentServiceModel setPlaceId(Long placeId) {
        this.placeId = placeId;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
