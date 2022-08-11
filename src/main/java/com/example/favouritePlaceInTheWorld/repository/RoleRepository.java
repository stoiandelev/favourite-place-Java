package com.example.favouritePlaceInTheWorld.repository;

import com.example.favouritePlaceInTheWorld.model.entity.RoleEntity;
import com.example.favouritePlaceInTheWorld.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(RoleEnum role);

}
