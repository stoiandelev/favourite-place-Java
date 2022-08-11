package com.example.favouritePlaceInTheWorld.service;

import com.example.favouritePlaceInTheWorld.model.entity.IpBlockedAddress;
import com.example.favouritePlaceInTheWorld.model.service.IpBlockedAddressServiceModel;

public interface IpBlockedAddressesService {

    IpBlockedAddress findByName(String name);

    void addBlockedIpToDB(IpBlockedAddressServiceModel ipBlockedAddressServiceModel);
}
