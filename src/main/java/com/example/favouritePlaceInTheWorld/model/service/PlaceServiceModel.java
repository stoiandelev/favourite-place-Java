package com.example.favouritePlaceInTheWorld.model.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlaceServiceModel {

    private Long id;

    @Size(min = 2, message = "Name must be minimum 6 character!")
    @NotNull(message = "Name can not be empty!")
    private String name;

    @NotNull(message = "ImageURL can not be empty!")
    private String imageURL;

    @Size(min = 6, message = "Description must be a least 6 symbols!")
    private String description;

    public PlaceServiceModel() {
    }

    public String getName() {
        return name;
    }

    public PlaceServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public PlaceServiceModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public PlaceServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public PlaceServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

}
