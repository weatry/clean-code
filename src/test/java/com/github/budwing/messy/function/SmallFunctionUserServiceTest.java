package com.github.budwing.messy.function;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SmallFunctionUserServiceTest {

    @Test
    void registerUser_blankUsername_returnsError() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.registerUser("", "password123", "a@b.com");
        assertEquals("Username cannot be blank", result);
    }

    @Test
    void registerUser_shortPassword_returnsError() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.registerUser("alice", "123", "a@b.com");
        assertEquals("Password must be at least 6 characters", result);
    }

    @Test
    void registerUser_invalidEmail_returnsError() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.registerUser("alice", "password123", "not-an-email");
        assertEquals("Invalid email format", result);
    }

    @Test
    void registerUser_success_returnsSuccess() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.registerUser("john", "securePass123", "john.doe@example.com");
        assertEquals("Registration successful", result);
    }
}
