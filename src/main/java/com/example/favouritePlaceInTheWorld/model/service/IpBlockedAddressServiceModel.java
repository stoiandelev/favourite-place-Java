package com.example.favouritePlaceInTheWorld.model.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class IpBlockedAddressServiceModel {

    @NotBlank
    @Size(min = 10)
    private String textContent;

    public IpBlockedAddressServiceModel() {
    }

    public String getTextContent() {
        return textContent;
    }

    public IpBlockedAddressServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}
