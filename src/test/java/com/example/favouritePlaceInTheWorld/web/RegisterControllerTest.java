package com.example.favouritePlaceInTheWorld.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

    /**
     * We need from Spring Security test dep
     * <dependency>
     *     <groupId>org.springframework.security</groupId>
     *     <artifactId>spring-security-test</artifactId>
     *     <version>5.5.2</version>
     *     <scope>test</scope>
     * </dependency>
     */

    @Autowired
    private MockMvc mockMvc;



    @Test
    void testRegistrationPageShow() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register-page"));
    }

    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("fullName", "Stoyan Delev")
                        .param("username", "stoian")
                        .param("email", "stoian@stoian")
                        .param("password", "1111")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

}
