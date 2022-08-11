package com.example.favouritePlaceInTheWorld.repository;

import com.example.favouritePlaceInTheWorld.model.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {


}
