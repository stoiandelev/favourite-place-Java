package com.example.favouritePlaceInTheWorld.service.impl;

import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouritePlaceUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public FavouritePlaceUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // The purpose of this method is to map our user representation (UserEntity)
        // to the user presentation in the Spring Security world -> UserDetails
        // The only thing that will provide to us is the username.
        // The username will come from the HTML login form

        UserEntity userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username " + username + "not found!"));

        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        // GrantedAuthority is the representation of the user role in the Spring world
        // SimpleGrantedAuthority is REQUIRED to be ROLE_ + role is an implementation of GrantedAuthority which spring provides
        // for our convenience
        // Out representation role is UserRoleEntity

        List<GrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toList());

        // User is the Spring implementation of UserDetails interface.
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}





