package com.example.favouritePlaceInTheWorld.service;

import com.example.favouritePlaceInTheWorld.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
