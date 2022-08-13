package com.example.favouritePlaceInTheWorld.service;

import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    UserEntity findByEmail(String email);

    void registerAndLogin(UserServiceModel userServiceModel);

    UserEntity findByUserName(String username);
}
