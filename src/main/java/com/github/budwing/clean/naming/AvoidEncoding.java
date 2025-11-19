package com.github.budwing.clean.naming;

/**
 * There are some naming encoding conventions due to historical reasons or language limitations.
 * For example, Hungarian Notation prefixes variable names with type information (e.g., "strName" for a string).
 * This practice can lead to confusion and reduced code readability, especially in modern programming languages.
 * Because of advancements in IDEs and type inference, such encoding is often unnecessary and can clutter code.
 * Instead, it's generally better to use clear and descriptive names that convey the purpose of the variable.
 * 
 * Another example is using prefixes like "m_" for member variables or "s_" for static variables.
 * While these conventions can help distinguish variable scopes, they can also make code harder to read and maintain.
 * Modern programming practices favor using language features (like "this" keyword) and proper naming conventions.
 * 
 * Some developers may use encoding to indicate variable roles, such as "is" for booleans (e.g., "isActive").
 * While this can enhance clarity, overuse of such prefixes can lead to verbose and less readable code. 
 */
public class AvoidEncoding {
    public static final String SLOGAN = "AVOID ENCODING";

    /**
     * Compared to strName, name is a more descriptive and clearer variable name.
     */
    private String name = "John Doe";

    /**
     * Compared to m_age, age is a more descriptive and clearer variable name.
     */
    private int age = 30;

    /**
     * Compared to isActive, active is a more concise variable name while still conveying the boolean nature.
     */
    private boolean active = true;
}

/**
 * Compared to IRunnable, Runnable is a more descriptive and clearer interface name.
 */
interface Runnable {
    void run();
}

/**
 * Compared to RunnableImpl, PrintRunnable(Printer may be better) describes what it does for this implementation.
 */
class PrintRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Running...");
    }
}