package com.example.favouritePlaceInTheWorld.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class BaseEntityTest {

    @Test
    void setIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        Assertions.assertEquals(1L, userEntity.getId());
    }
}
