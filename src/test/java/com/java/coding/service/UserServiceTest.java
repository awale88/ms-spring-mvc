package com.java.coding.service;

import com.java.coding.entity.User;
import com.java.coding.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Logger logger;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = User.builder()
                .id(new ObjectId())
                .name("abc")
                .password("abc")
                .build();
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
        verify(logger, never()).info("New User Created");
    }

    @Test
    void testFindAll() {

        List<User> list = List.of(
                new User(new ObjectId(), "1", "1"),
                new User(new ObjectId(), "2", "2"));
        when(userRepository.findAll()).thenReturn(list);

        List<User> result = userService.getAllUsers();

        assertEquals(list, result);
        verify(userRepository, times(1)).findAll();
        verify(logger, never()).info("List of all User was given");
    }
}
