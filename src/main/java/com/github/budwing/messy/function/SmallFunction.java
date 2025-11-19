package com.github.budwing.messy.function;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import com.github.budwing.User;

public class SmallFunction {
    public static final String SLOGAN = "Small functions are beautiful!";

    public static class UserService {

        /**
         * It's not SMALL, it does a lot of things:
         * 1. Validate input
         * 2. Check if username or email already exists
         * 3. Hash password
         * 4. Create and populate user object
         * 5. Save user to database
         * 6. Send welcome email
         * 7. Return result message
         * 
         * @param username
         * @param password
         * @param email
         * @return
         */
        public String registerUser(String username, String password, String email) {
            if (username == null || username.trim().isEmpty()) {
                return "Username cannot be blank";
            }
            if (password == null || password.length() < 6) {
                return "Password must be at least 6 characters";
            }
            if (email == null || !email.contains("@")) {
                return "Invalid email format";
            }

            if (DB.userExists(username)) {
                return "Username already exists";
            }
            if (DB.emailExists(email)) {
                return "Email already registered";
            }

            String hashedPassword = "";
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b));
                }
                hashedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                return "Failed to hash password";
            }


            User user = new User();
            user.setUsername(username);
            user.setPassword(hashedPassword);
            user.setEmail(email);
            user.setCreatedAt(new Date());
            user.setActive(true);

            try {
                DB.saveUser(user);
            } catch (Exception e) {
                return "Registration failed, please try again later";
            }

            try {
                EmailService.sendWelcomeEmail(email, username);
            } catch (Exception e) {
                System.out.println("Failed to send welcome email: " + e.getMessage());
            }

            return "Registration successful";
        }
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        String result = userService.registerUser("john_doe", "securePass123", "john.doe@example.com");
        System.out.println(result);
    }
}

class DB {
    public static boolean saveUser(User user) {
        // Simulate database save operation
        System.out.println("User " + user.getUsername() + " saved to database.");
        return true;
    }

    public static boolean userExists(String username) {
        // Simulate database check
        return false;
    }

    public static boolean emailExists(String email) {
        // Simulate database check
        return false;
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        // Simulate fetching user from database
        if ("testuser".equals(username) && "password123".equals(password)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }

        return null;
    }

    public static void updateUser(User user) {
        // Simulate updating user in database
        System.out.println("User " + user.getUsername() + " updated in database.");
    }
}

class EmailService {
    public static void sendWelcomeEmail(String email, String username) {
        // send welcome email
        System.out.println("send welcome mail to: " + email);

        String subject = "Welcome to Our Service, " + username + "!";
        String body = "Dear " + username + ",\n\n" +
                      "Thank you for registering with our service. We're excited to have you on board!\n\n" +
                      "Best regards,\n" +
                      "The Team";
        sendEmail(email, "system@noreply", username, subject, body, false, null, "smtp.example.com", 25, "smtp_user", "smtp_password");
    }

    public static boolean sendEmail(
        String to, 
        String from, 
        String replyTo, 
        String subject, 
        String body, 
        boolean isHtml, 
        List<String> attachments, 
        String smtpHost, 
        int smtpPort, 
        String username, 
        String password) {

        // sending email logic
        System.out.println("Sending email from " + from + " to " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        System.out.println("Using SMTP: " + smtpHost + ":" + smtpPort);

        // actual sending logic...
        return true;
    }
}

/**
 * Simulated Session class with side effect method
 */
class Session {
    public static void initialize(String username) {
        // Simulate session initialization
        System.out.println("Session initialized for user: " + username);
    }
    
}

