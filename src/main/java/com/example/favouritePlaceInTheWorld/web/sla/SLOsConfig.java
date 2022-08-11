package com.example.favouritePlaceInTheWorld.web.sla;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "slos-config")
public class SLOsConfig {

    private List<SLOConfig> slos = new ArrayList<>();

    public List<SLOConfig> getSlos() {
        return slos;
    }

    public SLOsConfig setSlos(List<SLOConfig> slos) {
        this.slos = slos;
        return this;
    }

    public static class SLOConfig {
        private String id;
        private Integer threshold;

        public String getId() {
            return id;
        }

        public SLOConfig setId(String id) {
            this.id = id;
            return this;
        }

        public Integer getThreshold() {
            return threshold;
        }

        public SLOConfig setThreshold(Integer threshold) {
            this.threshold = threshold;
            return this;
        }
    }
}
