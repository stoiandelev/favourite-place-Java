package com.example.favouritePlaceInTheWorld.config;


import com.example.favouritePlaceInTheWorld.web.interceptors.IpBlackListInterceptor;
import com.example.favouritePlaceInTheWorld.web.interceptors.MaintenanceInterceptor;
import com.example.favouritePlaceInTheWorld.web.interceptors.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurationInterceptor implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;
    private final IpBlackListInterceptor ipBlackListInterceptor;
    private final MaintenanceInterceptor maintenanceInterceptor;

    public WebConfigurationInterceptor(StatsInterceptor statsInterceptor,
                                       IpBlackListInterceptor ipBlackListInterceptor,
                                       MaintenanceInterceptor maintenanceInterceptor) {
        this.statsInterceptor = statsInterceptor;
        this.ipBlackListInterceptor = ipBlackListInterceptor;
        this.maintenanceInterceptor = maintenanceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
        registry.addInterceptor(ipBlackListInterceptor);
        registry.addInterceptor(maintenanceInterceptor);
    }
}
