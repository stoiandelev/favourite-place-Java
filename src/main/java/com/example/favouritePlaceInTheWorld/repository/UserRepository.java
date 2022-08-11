package com.example.favouritePlaceInTheWorld.repository;

import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByActive(boolean active);

}
