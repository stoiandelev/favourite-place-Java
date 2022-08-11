package com.example.favouritePlaceInTheWorld.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IpBlockedAddressTest {

    @Test
    void testSetBlockerIp(){
        IpBlockedAddress ipBlockedAddress = new IpBlockedAddress();
        ipBlockedAddress.setName("1.1.1.1");
        Assertions.assertEquals("1.1.1.1", ipBlockedAddress.getName());
    }

}
