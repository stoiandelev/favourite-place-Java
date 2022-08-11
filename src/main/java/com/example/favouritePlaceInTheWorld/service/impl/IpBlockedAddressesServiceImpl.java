package com.example.favouritePlaceInTheWorld.service.impl;

import com.example.favouritePlaceInTheWorld.model.entity.IpBlockedAddress;
import com.example.favouritePlaceInTheWorld.model.service.IpBlockedAddressServiceModel;
import com.example.favouritePlaceInTheWorld.repository.IpBlockedAddressesRepository;
import com.example.favouritePlaceInTheWorld.service.IpBlockedAddressesService;
import org.springframework.stereotype.Service;

@Service
public class IpBlockedAddressesServiceImpl implements IpBlockedAddressesService {

    private final IpBlockedAddressesRepository ipBlockedAddressesRepository;

    public IpBlockedAddressesServiceImpl(IpBlockedAddressesRepository ipBlockedAddressesRepository) {
        this.ipBlockedAddressesRepository = ipBlockedAddressesRepository;
    }

    @Override
    public IpBlockedAddress findByName(String name) {
        return ipBlockedAddressesRepository
                .findByName(name)
                .orElse(null);
    }

    @Override
    public void addBlockedIpToDB(IpBlockedAddressServiceModel ipBlockedAddressServiceModel) {
        IpBlockedAddress ipBlockedAddress = new IpBlockedAddress();

        ipBlockedAddress.setName(ipBlockedAddressServiceModel.getTextContent());

        ipBlockedAddressesRepository.save(ipBlockedAddress);
    }
}
