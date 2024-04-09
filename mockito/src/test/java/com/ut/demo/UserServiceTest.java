package com.ut.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    UserService userService;

    @Test
    void sum() {
        MockitoAnnotations.openMocks(this);
        userService.sum(10,20);

    }
}