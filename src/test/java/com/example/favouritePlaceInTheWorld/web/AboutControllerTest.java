package com.example.favouritePlaceInTheWorld.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AboutControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    void testAboutPageShow() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about-us"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAboutPageShowDetails() throws Exception {
        mockMvc.perform(get("/about/details"))
                .andExpect(view().name("about-us-details"));
    }
}
