package com.example.favouritePlaceInTheWorld.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlaceAddBindingModel {

    @Size(min = 2, message = "Name must be minimum 6 character!")
    @NotNull(message = "Name can not be empty!")
    private String name;

    @NotBlank(message = "ImageURL can not be empty!")
    private String imageURL;

    @Size(min = 6, message = "Description must be a least 6 symbols!")
    private String description;

    public PlaceAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public PlaceAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public PlaceAddBindingModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlaceAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
