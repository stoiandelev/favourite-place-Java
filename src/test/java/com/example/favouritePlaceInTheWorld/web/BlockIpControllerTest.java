package com.example.favouritePlaceInTheWorld.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BlockIpControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    @WithMockUser(roles = "ADMIN")
    void testBlockIpShow() throws Exception {
        mockMvc.perform(get("/block-ip-addresses"))
                .andExpect(status().isOk())
                .andExpect(view().name("blockIp-page"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testBlockIp_SuccessfulAddInDB() throws Exception {
        mockMvc.perform(post("/block-ip-addresses")
                        .param("textContent", "1111111111")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testBlockIp_UN_SuccessfulAddInDB() throws Exception {
        mockMvc.perform(post("/block-ip-addresses")
                        .param("textContent", "11")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("block-ip-addresses"));
    }
}
