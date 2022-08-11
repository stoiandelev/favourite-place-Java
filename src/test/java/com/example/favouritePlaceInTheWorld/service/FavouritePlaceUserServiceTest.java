package com.example.favouritePlaceInTheWorld.service;

import com.example.favouritePlaceInTheWorld.model.entity.RoleEntity;
import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.model.enums.RoleEnum;
import com.example.favouritePlaceInTheWorld.repository.UserRepository;
import com.example.favouritePlaceInTheWorld.service.impl.FavouritePlaceUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FavouritePlaceUserServiceTest {

    /**
     * we need instants on oue service;
     *
     * @ExtendWith(MockitoExtension.class) -> part from JUnit 5 framework,for work with
     * Mockito framework easy.Mockito is framework for create on Mock.-> for "fake"
     * object.
     * This is not Real Object this is Mock, we are learning what to do.
     * We use ARRANGE, ACTION and ASSERT
     */

    @Mock
    private UserRepository mockUserRepo;

    private FavouritePlaceUserService toTest;

    @BeforeEach
    void setUp() {
        toTest = new FavouritePlaceUserService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExits() {
        //ARRANGE
        UserEntity testUserEntity =
                new UserEntity()
                        .setUsername("stoian")
                        .setPassword("1111")
                        .setRoles(
                                Set.of(new RoleEntity().setRole(RoleEnum.USER),
                                        new RoleEntity().setRole(RoleEnum.ADMIN)));

        when(mockUserRepo.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        //ACTION
        UserDetails userDetails = toTest.loadUserByUsername(testUserEntity.getUsername());

        //ASSERT
        Assertions.assertEquals(testUserEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());

        var authorities = userDetails.getAuthorities();
        var authoritiesIterator = authorities.iterator();
        Assertions.assertEquals(2, authorities.size());
        Assertions.assertEquals("ROLE_" + RoleEnum.ADMIN.name(),
                authoritiesIterator.next().getAuthority());
        Assertions.assertEquals("ROLE_" + RoleEnum.USER.name(),
                authoritiesIterator.next().getAuthority());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExits() {
        //arrange
        //No need arrange anything, mock return empty optional.

        //act && assert
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("NoExist"));
    }
}
