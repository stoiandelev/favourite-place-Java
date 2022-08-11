package com.example.favouritePlaceInTheWorld.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Component
public class MaintenanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        var requestURI = request.getRequestURI();

        LocalTime now = LocalTime.now();
        if (!requestURI.equals("/maintenance")) {
            if (now.getHour() >= 22) {
                response.sendRedirect("/maintenance");
                return false;
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


}
