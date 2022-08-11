package com.example.favouritePlaceInTheWorld.model.binding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlaceAddBindingModelTest {

    @Test
    void setName() {
        PlaceAddBindingModel placeAddBindingModel = new PlaceAddBindingModel();
        placeAddBindingModel.setName("test");
        Assertions.assertEquals(placeAddBindingModel.getName(), "test");
    }

    @Test
    void setImageURL() {
        PlaceAddBindingModel placeAddBindingModel = new PlaceAddBindingModel();
        placeAddBindingModel.setImageURL("/test");
        Assertions.assertEquals(placeAddBindingModel.getImageURL(), "/test");
    }

    @Test
    void setDescription() {
        PlaceAddBindingModel placeAddBindingModel = new PlaceAddBindingModel();
        placeAddBindingModel.setDescription("test");
        Assertions.assertEquals(placeAddBindingModel.getDescription(), "test");
    }


}
