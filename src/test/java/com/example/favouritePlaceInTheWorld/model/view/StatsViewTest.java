package com.example.favouritePlaceInTheWorld.model.view;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatsViewTest {



    @Test
    void getAnonymousRequest() {
        StatsView statsView = new StatsView(1, 1);
        int anonymousRequest1 = statsView.getAnonymousRequest();
        Assertions.assertEquals(statsView.getAnonymousRequest(), anonymousRequest1);
    }

    @Test
    void getTotalRequest() {
        StatsView statsView = new StatsView(1, 1);
        int totalRequest = statsView.getTotalRequest();
        Assertions.assertEquals(statsView.getTotalRequest(), totalRequest);
    }

    @Test
    void getAuthRequest() {
        StatsView statsView = new StatsView(1, 1);
        int totalRequest = statsView.getTotalRequest();
        Assertions.assertEquals(statsView.getTotalRequest(), totalRequest);
    }
}
