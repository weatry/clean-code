package com.github.budwing.clean.concurrency.pattern;

/**
 * Double Checked Locking(DCL) is a concurrency pattern used to reduce the overhead of acquiring a lock by first testing
 * the locking criterion (the "lock hint") without actually acquiring the lock. Only if the check indicates that
 * locking is required does the actual lock proceed. This pattern is often used in the context of lazy initialization
 * of resources, such as singleton instances. 
 * 
 * However, it is important to note that the Double Checked Locking pattern can be problematic in some programming
 * languages due to issues like instruction reordering and visibility of shared variables across threads. In Java,
 * for instance, the pattern requires the use of the `volatile` keyword to ensure that the instance variable is correctly
 * published to other threads. Without proper handling, it can lead to subtle bugs and inconsistencies.
 * @see <a href="https://en.wikipedia.org/wiki/Double-checked_locking">Double-checked locking - Wikipedia</a>
 * 
 */
public class DoubleCheckedLocking {
    private static volatile DoubleCheckedLocking instance;

    private DoubleCheckedLocking() {
        // Private constructor to prevent instantiation
    }

    public static DoubleCheckedLocking getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }
}
