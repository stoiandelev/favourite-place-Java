package com.example.favouritePlaceInTheWorld.model.view;

public class StatsView {

    private final int anonymousRequest;
    private final int authRequest;


    public StatsView(Integer anonymousRequest, Integer authRequest) {
        this.anonymousRequest = anonymousRequest;
        this.authRequest = authRequest;
    }

    public int getTotalRequest() {
        return anonymousRequest + authRequest;
    }

    public int getAnonymousRequest() {
        return anonymousRequest;
    }


    public int getAuthRequest() {
        return authRequest;
    }




}
