package com.example.favouritePlaceInTheWorld.service;


import com.example.favouritePlaceInTheWorld.model.entity.PlaceEntity;
import com.example.favouritePlaceInTheWorld.model.service.PlaceServiceModel;
import com.example.favouritePlaceInTheWorld.model.view.PlaceView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceService {

    void add(PlaceServiceModel placeServiceModel);

    Page<PlaceView> findAllPlaces(Pageable pageable);

    //for details places
    PlaceView findById(Long id);

    void deleteById(Long id);

    PlaceEntity findByIdPlaceOwner(Long id);

}
