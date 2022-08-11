package com.example.favouritePlaceInTheWorld.initDB;

import com.example.favouritePlaceInTheWorld.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements CommandLineRunner {


    private final RoleService roleService;

    public DataBaseInitializer(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
    }
}
