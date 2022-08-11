package com.example.favouritePlaceInTheWorld.service;

import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    UserServiceModel findByEmailAndPassword(String email, String password);

    UserEntity findByEmail(String email);

    void registerAndLogin(UserServiceModel userServiceModel);

    UserEntity findByUserName(String username);
}
