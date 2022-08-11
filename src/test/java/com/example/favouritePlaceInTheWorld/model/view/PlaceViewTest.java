package com.example.favouritePlaceInTheWorld.model.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlaceViewTest {

    @Test
    void setId() {
        PlaceView placeView = new PlaceView();
        placeView.setId(1L);
        Assertions.assertEquals(placeView.getId(), 1);
    }

    @Test
    void setName() {
        PlaceView placeView = new PlaceView();
        placeView.setName("Name");
        Assertions.assertEquals(placeView.getName(), "Name");
    }

    @Test
    void setImageURL() {
        PlaceView placeView = new PlaceView();
        placeView.setImageURL("/Name");
        Assertions.assertEquals(placeView.getImageURL(), "/Name");
    }

    @Test
    void setDescription() {
        PlaceView placeView = new PlaceView();
        placeView.setDescription("textIsHere");
        Assertions.assertEquals(placeView.getDescription(), "textIsHere");
    }
}
