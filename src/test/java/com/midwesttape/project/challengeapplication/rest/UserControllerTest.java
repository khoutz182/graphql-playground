package com.midwesttape.project.challengeapplication.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserAndAddress() throws Exception {
        mockMvc.perform(get("/v1/users/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Phil"))
                .andExpect(jsonPath("$.lastName").value("Ingwell"))
                .andExpect(jsonPath("$.username").value("PhilIngwell"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.address.address1").value("1060 W Addison St"));
    }

    /**
     * {@link Transactional} is required since this test updates the in memory database.
     * {@link Rollback} could be added for clarity too, but since it's unnecessary I left it out.
     */
    @Test
    @Transactional
    public void updateUser() throws Exception {
        long userId = 1;
        String payload = "{\"firstName\":\"Bart\",\"lastName\":\"Simpson\",\"username\":\"bsimpson\"}";

        mockMvc.perform(put("/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk());

        mockMvc.perform(get("/v1/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bart"))
                .andExpect(jsonPath("$.lastName").value("Simpson"));
    }

}