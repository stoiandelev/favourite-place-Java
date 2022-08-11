package com.example.favouritePlaceInTheWorld.service.impl;

import com.example.favouritePlaceInTheWorld.model.entity.RoleEntity;
import com.example.favouritePlaceInTheWorld.model.enums.RoleEnum;
import com.example.favouritePlaceInTheWorld.repository.RoleRepository;
import com.example.favouritePlaceInTheWorld.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            Arrays
                    .stream(RoleEnum.values())
                    .forEach(roleEnum -> {

                        RoleEntity newRole = new RoleEntity();

                        newRole.setRole(roleEnum);

                        roleRepository.save(newRole);
                    });
        }
    }
}
