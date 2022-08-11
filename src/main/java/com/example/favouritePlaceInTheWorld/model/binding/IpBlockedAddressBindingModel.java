package com.example.favouritePlaceInTheWorld.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class IpBlockedAddressBindingModel {

    @Size(min = 10, message = "Ip address must be min 10 symbols!")
    @NotBlank(message = "Can not be empty!")
    private String textContent;

    public IpBlockedAddressBindingModel() {
    }

    public String getTextContent() {
        return textContent;
    }

    public IpBlockedAddressBindingModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}

