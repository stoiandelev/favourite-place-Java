package com.example.favouritePlaceInTheWorld.web.interceptors;

import com.example.favouritePlaceInTheWorld.model.entity.IpBlockedAddress;
import com.example.favouritePlaceInTheWorld.service.IpBlockedAddressesService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IpBlackListInterceptor implements HandlerInterceptor {

    private final IpBlockedAddressesService ipBlockedAddressesService;

    public IpBlackListInterceptor(IpBlockedAddressesService ipBlockedAddressesService) {
        this.ipBlockedAddressesService = ipBlockedAddressesService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        IpBlockedAddress blockedIp = ipBlockedAddressesService.findByName(ipAddress);
        return blockedIp == null;
    }
}
