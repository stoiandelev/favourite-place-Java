package com.example.favouritePlaceInTheWorld.service.impl;

import com.example.favouritePlaceInTheWorld.model.entity.PlaceEntity;
import com.example.favouritePlaceInTheWorld.model.entity.RoleEntity;
import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.model.enums.RoleEnum;
import com.example.favouritePlaceInTheWorld.model.service.PlaceServiceModel;
import com.example.favouritePlaceInTheWorld.model.view.PlaceView;
import com.example.favouritePlaceInTheWorld.repository.PlaceRepository;
import com.example.favouritePlaceInTheWorld.repository.UserRepository;
import com.example.favouritePlaceInTheWorld.service.PlaceService;
import com.example.favouritePlaceInTheWorld.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void add(PlaceServiceModel placeServiceModel) {
        PlaceEntity place = modelMapper.map(placeServiceModel, PlaceEntity.class);
        place.setCreated(LocalDateTime.now());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<UserEntity> findByUserName = userRepository.findByUsername(username);
        UserEntity entity = findByUserName.get();

        place.setUserCreate(entity);

        placeRepository.save(place);
    }

    @Override
    public Page<PlaceView> findAllPlaces(Pageable pageable) {
        return placeRepository
                .findAll(pageable)
                .map(placeEntity -> modelMapper.map(placeEntity, PlaceView.class));
    }


    @Override
    public PlaceView findById(Long id) {
        return placeRepository
                .findById(id)
                .map(placeEntity -> modelMapper.map(placeEntity, PlaceView.class))
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Long ownerPlaceId = null;
        Long currentUserId = null;

        //This is for current User ID (Principal)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<UserEntity> findByUserName = userRepository.findByUsername(username);
        UserEntity entity = findByUserName.get();
        currentUserId = entity.getId();

        //This is for Place owner id
        Optional<PlaceEntity> currentRecipeId = placeRepository.findById(id);
        PlaceEntity currentRecipe = currentRecipeId.get();
        ownerPlaceId = currentRecipe.getUserCreate().getId();


        if (ownerPlaceId.equals(currentUserId) || entity.getEmail().equals("stoiandelev89@gmail.com")) {
            placeRepository.deleteById(id);
        }
    }

    @Override
    public PlaceEntity findByIdPlaceOwner(Long id) {
        return placeRepository
                .findById(id)
                .orElse(null);
    }


}
