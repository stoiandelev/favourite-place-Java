package com.example.favouritePlaceInTheWorld.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PlaceEntityTest {

    @Test
    void setImageURL() {
        PlaceEntity place = new PlaceEntity();
        place.setImageURL("/this.pc");
        Assertions.assertEquals(place.getImageURL(), "/this.pc");
    }

    @Test
    void setDescription() {
        PlaceEntity place = new PlaceEntity();
        place.setDescription("description");
        Assertions.assertEquals(place.getDescription(), "description");
    }

    @Test
    void setCreated() {
        PlaceEntity place = new PlaceEntity();
        LocalDateTime time = LocalDateTime.of(2007,8,31,12,11);
        place.setCreated(time);
        Assertions.assertEquals(place.getCreated(), time);
    }

    @Test
    void setUserCreate() {
        PlaceEntity place = new PlaceEntity();
        UserEntity user = new UserEntity();
        user.setUsername("Stoian");
        place.setUserCreate(user);
        Assertions.assertEquals(place.getUserCreate().getUsername(), "Stoian");
    }

    @Test
    void setComments() {
        PlaceEntity place = new PlaceEntity();
        CommentEntity comment = new CommentEntity();
        List<CommentEntity> comments = new ArrayList<>();
        comments.add(comment);
        place.setComments(comments);
        Assertions.assertEquals(place.getComments().size(), 1);
    }


}
