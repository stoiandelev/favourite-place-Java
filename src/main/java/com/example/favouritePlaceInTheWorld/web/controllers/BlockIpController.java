package com.example.favouritePlaceInTheWorld.web.controllers;

import com.example.favouritePlaceInTheWorld.model.binding.IpBlockedAddressBindingModel;
import com.example.favouritePlaceInTheWorld.model.binding.UserRegisterBindingModel;
import com.example.favouritePlaceInTheWorld.model.entity.IpBlockedAddress;
import com.example.favouritePlaceInTheWorld.model.service.IpBlockedAddressServiceModel;
import com.example.favouritePlaceInTheWorld.service.IpBlockedAddressesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BlockIpController {

    private final IpBlockedAddressesService ipBlockedAddressesService;
    private final ModelMapper modelMapper;

    public BlockIpController(IpBlockedAddressesService ipBlockedAddressesService, ModelMapper modelMapper) {
        this.ipBlockedAddressesService = ipBlockedAddressesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/block-ip-addresses")
    public String blockedIp() {
        return "blockIp-page";
    }

    @PostMapping("/block-ip-addresses")
    public String blockedIdConfirm(@Valid IpBlockedAddressBindingModel ipBlockedAddressBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ipBlockedAddressBindingModel", ipBlockedAddressBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ipBlockedAddressBindingModel", bindingResult);
            return "redirect:block-ip-addresses";
        }

        ipBlockedAddressesService.addBlockedIpToDB(
                modelMapper.map(ipBlockedAddressBindingModel, IpBlockedAddressServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute()
    public IpBlockedAddressBindingModel ipBlockedAddressBindingModel() {
        return new IpBlockedAddressBindingModel();
    }
}
