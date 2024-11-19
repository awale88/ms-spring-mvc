package com.java.coding.service;

import com.java.coding.entity.User;
import com.java.coding.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
        logger.info("New User Created");
    }

    public List<User> getAllUsers() {
        logger.info("List of all User was given");
        return userRepository.findAll();
    }
}
