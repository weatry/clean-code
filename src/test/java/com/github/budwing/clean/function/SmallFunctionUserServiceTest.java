package com.github.budwing.clean.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.github.budwing.User;

public class SmallFunctionUserServiceTest {

    @Test
    void registerUser_success_returnsSuccess() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.registerUser("john", "securePass123", "john.doe@example.com");
        assertEquals("Registration successful", result);
    }

    @Test
    void validateInput_allValid_returnsNull() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.validateInput("validUser", "validPass123", "valid@example.com");
        assertNull(result);
    }

    @Test
    void validateInput_missingUsername_returnsError() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.validateInput("", "validPass123", "valid@example.com");
        assertEquals("Username cannot be blank", result);
    }

    @Test
    void validateInput_missingPassword_returnsError() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.validateInput("validUser", "", "valid@example.com");
        assertEquals("Password must be at least 6 characters", result);
    }

    @Test
    void checkUniqueness_newUser_returnsNull() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.checkUniqueness("newUser", "newUser@example.com");
        assertNull(result);
    }

    @Test
    void hashPassword_validPassword_returnsHashed() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        String result = svc.hashPassword("validPass123");
        assertEquals(64, result.length());
    }

    @Test
    void buildUser_validInput_returnsUser() {
        SmallFunction.UserService svc = new SmallFunction.UserService();
        User user = svc.buildUser("testUser", "hashedPass", "testUser@example.com");
        assertEquals("testUser", user.getUsername());
    }
}
