package com.example.favouritePlaceInTheWorld.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceModelTest {

    @Test
    void setName() {
        PlaceServiceModel placeServiceModel = new PlaceServiceModel();
        placeServiceModel.setName("haskovo");
        Assertions.assertEquals(placeServiceModel.getName(), "haskovo");
    }

    @Test
    void setImageUrl() {
        PlaceServiceModel placeServiceModel = new PlaceServiceModel();
        placeServiceModel.setImageURL("/haskovo");
        Assertions.assertEquals(placeServiceModel.getImageURL(), "/haskovo");
    }

    @Test
    void setDescription() {
        PlaceServiceModel placeServiceModel = new PlaceServiceModel();
        placeServiceModel.setDescription("text");
        Assertions.assertEquals(placeServiceModel.getDescription(), "text");
    }

    @Test
    void setId() {
        PlaceServiceModel placeServiceModel = new PlaceServiceModel();
        placeServiceModel.setId(1L);
        Assertions.assertEquals(placeServiceModel.getId(), 1);
    }
}
