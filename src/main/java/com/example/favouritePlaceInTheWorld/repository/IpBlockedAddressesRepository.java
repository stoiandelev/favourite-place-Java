package com.example.favouritePlaceInTheWorld.repository;

import com.example.favouritePlaceInTheWorld.model.entity.IpBlockedAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpBlockedAddressesRepository extends JpaRepository<IpBlockedAddress, Long> {

    Optional<IpBlockedAddress> findByName(String name);
}
