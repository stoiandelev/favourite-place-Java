package com.example.favouritePlaceInTheWorld.model.view;

public class PlaceView {

    private Long id;
    private String name;
    private String imageURL;
    private String description;

    public PlaceView() {
    }

    public Long getId() {
        return id;
    }

    public PlaceView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlaceView setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public PlaceView setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlaceView setDescription(String description) {
        this.description = description;
        return this;
    }
}
