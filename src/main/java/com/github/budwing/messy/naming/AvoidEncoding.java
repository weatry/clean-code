package com.github.budwing.messy.naming;

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
     * Example of Hungarian Notation encoding.
     * The prefix "str" indicates that the variable is a string.
     * This encoding is unnecessary and can be avoided by using a more descriptive name.
     */
    private String strName = "John Doe";

    /**
     * Example of member variable encoding.
     * The prefix "m_" indicates that the variable is a member variable.
     * This encoding can be avoided by using the "this" keyword to refer to member variables.
     */
    private int m_age = 30;

    /**
     * Example of boolean variable encoding.
     * The prefix "is" indicates that the variable is a boolean.
     * While this can enhance clarity, it's important to use it judiciously to avoid verbosity.
     */
    private boolean isActive = true;
}

/**
 * It's not recommended to use prefix 'I' to indicate an interface.
 * Because in modern programming languages, interfaces are easily distinguishable by their declaration.
 * Using 'I' prefix can lead to confusion and reduce code readability.
 */
interface IRunnable {
    void run();
}

/**
 * It's not recommended to use suffix 'Impl' to indicate an implementation class.
 * Because it can lead to confusion and reduce code readability.
 * Instead, it's better to use descriptive names that convey the purpose of the class.
 * 
 * For example, instead of naming a class 'RunnableImpl', it's better to name it 'FileRunnable' or 'NetworkRunnable'
 * to indicate its specific functionality. You can use 'DefaultRunnable' if no better name is available.
 */
class RunnableImpl implements IRunnable {
    @Override
    public void run() {
        System.out.println("Running...");
    }
}