package com.example.favouritePlaceInTheWorld.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about() {
        return "about-us";
    }

    @GetMapping("/about/details")
    public String aboutDetails() {
        return "about-us-details";
    }
}
