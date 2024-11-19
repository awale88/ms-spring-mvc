package com.java.coding.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicController.class)
public class PublicControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testHelloWorldEndpoint() throws Exception {
        mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello From Controller."));
    }

    @Test
    public void testSecondEndpoint() throws Exception {
        mockMvc.perform(get("/api/test2")
                        .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(equalTo("This is Home page")));
    }
}
