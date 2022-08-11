package com.example.favouritePlaceInTheWorld.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ip_blocked_address")
public class IpBlockedAddress extends BaseEntity{

    private String name;

    public IpBlockedAddress() {
    }

    public String getName() {
        return name;
    }

    public IpBlockedAddress setName(String name) {
        this.name = name;
        return this;
    }
}
