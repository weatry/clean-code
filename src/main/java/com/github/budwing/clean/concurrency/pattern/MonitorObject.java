package com.github.budwing.clean.concurrency.pattern;

/**
 * Monitor Object is a concurrency pattern that encapsulates an object's state and provides synchronized access to it.
 * The Monitor Object pattern ensures that only one thread can access the object's methods at a time, preventing race conditions
 * and ensuring data consistency. It typically involves using synchronized methods or blocks to control access to the object's state.
 * This pattern is particularly useful in multi-threaded environments where multiple threads may attempt to read or modify the state of an object simultaneously.
 * By using the Monitor Object pattern, developers can simplify the design of concurrent systems and reduce the likelihood of synchronization-related bugs.
 * But it's important to use this pattern judiciously, as excessive synchronization can lead to performance bottlenecks and reduced concurrency.
 * @see <a href="https://en.wikipedia.org/wiki/Monitor_(synchronization)">Monitor (synchronization) - Wikipedia</a>
 */
public class MonitorObject {
    private int state;

    public synchronized int getState() {
        return state;
    }

    public synchronized void setState(int state) {
        this.state = state;
    }
}
