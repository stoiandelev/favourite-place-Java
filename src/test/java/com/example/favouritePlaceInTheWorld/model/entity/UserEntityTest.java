package com.example.favouritePlaceInTheWorld.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserEntityTest {

    @Test
    void setFullName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName("Stoian Delev");
        Assertions.assertEquals(userEntity.getFullName(), "Stoian Delev");
    }

    @Test
    void setActive() {
        UserEntity userEntity = new UserEntity();
        userEntity.setActive(true);
        Assertions.assertTrue(userEntity.isActive());
    }

    @Test
    void created() {
        UserEntity userEntity = new UserEntity();
        LocalDateTime time = LocalDateTime.of(2007,8,31,12,11);
        userEntity.setCreated(time);
        Assertions.assertEquals(userEntity.getCreated(), time);
    }

    @Test
    void setPlace() {
        UserEntity userEntity = new UserEntity();

        PlaceEntity place = new PlaceEntity();
        Set<PlaceEntity> places = new HashSet<>();
        places.add(place);

        UserEntity user = new UserEntity();
        userEntity.setPlaces(places);

        Assertions.assertNull(user.getPlaces());
    }
}
