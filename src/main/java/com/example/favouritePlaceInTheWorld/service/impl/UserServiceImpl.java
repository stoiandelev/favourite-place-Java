package com.example.favouritePlaceInTheWorld.service.impl;

import com.example.favouritePlaceInTheWorld.model.entity.RoleEntity;
import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.model.enums.RoleEnum;
import com.example.favouritePlaceInTheWorld.model.service.UserServiceModel;
import com.example.favouritePlaceInTheWorld.repository.RoleRepository;
import com.example.favouritePlaceInTheWorld.repository.UserRepository;
import com.example.favouritePlaceInTheWorld.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavouritePlaceUserService favouritePlaceUserService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           FavouritePlaceUserService favouritePlaceUserService, EmailService emailService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.favouritePlaceUserService = favouritePlaceUserService;
        this.emailService = emailService;
    }


    @Override
    public boolean register(UserServiceModel userServiceModel) {
        try {
            userRepository.save(modelMapper.map(userServiceModel, UserEntity.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public UserServiceModel findByEmailAndPassword(String email, String password) {
        return userRepository
                .findByEmailAndPassword(email, password)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElse(null);
    }

    @Override
    public void registerAndLogin(UserServiceModel userServiceModel) {

        RoleEntity userRole = roleRepository.findByRole(RoleEnum.ADMIN);

        UserEntity newUser = new UserEntity();

        newUser
                .setEmail(userServiceModel.getEmail())
                .setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                .setFullName(userServiceModel.getFullName())
                .setUsername(userServiceModel.getUsername())
                .setRoles(Set.of(userRole))
                .setCreated(LocalDateTime.now())
                .setActive(true);

        //This is for mailHog
//        emailService.sendRegistrationEmail(newUser.getEmail(), newUser.getUsername());

        userRepository.save(newUser);

        //this is Spring representation of user; principal is userDetails
        UserDetails principal = favouritePlaceUserService.loadUserByUsername(newUser.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    @Override
    public UserEntity findByUserName(String username) {
        return userRepository
                .findByUsername(username)
                .orElse(null);
    }

}



