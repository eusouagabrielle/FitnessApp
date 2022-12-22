package com.example.fitnessapp.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AthleteControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    // Integration test to return a JSON when a trainer does a get request for an athlete using its id
    @Test
    public void returnJson() throws Exception {
        mockMvc
                .perform(get("/workout/athlete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("jan")
                                .roles("TRAINER")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    // Integration test to get all the athletes that a trainer has using his/her id
    @Test
    public void getAthletesByTrainerId() throws Exception {
        mockMvc
                .perform(get("/workout/1/athletes")
                        .with(user("bas")
                                .roles("ATHLETE")))
                .andExpect(status().isOk());
    }



}
