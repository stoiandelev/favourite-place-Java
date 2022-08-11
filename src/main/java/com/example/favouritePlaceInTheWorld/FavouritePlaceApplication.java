package com.example.favouritePlaceInTheWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FavouritePlaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FavouritePlaceApplication.class, args);
    }

}
