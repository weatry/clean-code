package com.github.budwing.messy.exception;

/**
 * Error codes are outdated and inflexible. 
 * They make the code harder to read and maintain.
 * They often lead to duplicated error handling logic.
 * They do not provide enough context about the error.
 * 
 * Error codes are still used in some legacy systems, but modern practices favor exceptions or result objects.
 */
public class ErrorCode {
    public static int errorCodeMethod() {
        // Simulated error condition
        boolean errorCondition = true;
        if (errorCondition) {
            return 1001; // Error code indicating a specific error
        }
        return 0; // No error
    }

}
