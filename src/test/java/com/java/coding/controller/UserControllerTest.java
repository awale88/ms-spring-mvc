package com.java.coding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.coding.entity.User;
import com.java.coding.service.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {

        User user1 = createTestUser("Robert", "abc");
        User user2 = createTestUser("Juan", "lol");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/all").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].name").value("Robert"))
                .andExpect(jsonPath("$[1].name").value("Juan"))
                .andDo(print());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testCreateUser() throws Exception {
        User user = createTestUser("Ram", "Sita");

        doNothing().when(userService).createUser(any(User.class));

        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(any(User.class));
    }

    private User createTestUser(String name, String password) {
        return User.builder().id(new ObjectId()).name(name).password(password).build();
    }
}
