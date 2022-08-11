package com.example.favouritePlaceInTheWorld.web.controllers;


import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.repository.UserRepository;
import com.example.favouritePlaceInTheWorld.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/users/my-profile")
    public String userProfile(@AuthenticationPrincipal UserDetails user, Model model) {

        String username = user.getUsername();

        Optional<UserEntity> byUsername = Optional.ofNullable(userService.findByUserName(username));

        UserEntity entity = byUsername.get();

        String fullName = entity.getFullName();
        String email = entity.getEmail();

        model.addAttribute("fullName", fullName);
        model.addAttribute("email", email);


        return "user-profile-card";
    }


}
