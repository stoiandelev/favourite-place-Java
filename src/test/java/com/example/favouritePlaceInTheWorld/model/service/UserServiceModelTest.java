package com.example.favouritePlaceInTheWorld.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceModelTest {

    @Test
    void setPlaceId() {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setId(1L);
        Assertions.assertEquals(userServiceModel.getId(), 1);
    }
}
