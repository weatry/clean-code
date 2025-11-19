package com.github.budwing.clean.concurrency.pattern;

/**
 * Initialization Holder is a concurrency pattern used to achieve lazy initialization of resources in a thread-safe manner.
 * This pattern leverages the class loading mechanism of the Java Virtual Machine (JVM) to ensure that a resource is
 * initialized only when it is first accessed, and that the initialization is performed in a thread-safe way without
 * the need for explicit synchronization.
 * 
 * The Initialization Holder pattern typically involves creating a static inner class that holds the instance of the
 * resource to be initialized. The outer class provides a method to access the resource, which triggers the loading
 * of the inner class and, consequently, the initialization of the resource.
 * 
 * This pattern is particularly useful for implementing singletons or other resources that are expensive to create
 * and should only be instantiated when needed.
 * @see <a href="https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom">Initialization-on-demand holder idiom - Wikipedia</a>
 */
public class InitializationHolder {
    private InitializationHolder() {
        // Private constructor to prevent instantiation
    }

    private static class Holder {
        private static final InitializationHolder INSTANCE = new InitializationHolder();
    }

    public static InitializationHolder getInstance() {
        return Holder.INSTANCE;
    }
}
