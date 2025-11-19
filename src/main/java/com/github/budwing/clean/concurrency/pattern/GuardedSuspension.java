package com.github.budwing.clean.concurrency.pattern;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Guarded Suspension is a concurrency pattern that is used to manage access to an object or resource that may not be immediately available.
 * The pattern involves "guarding" a method or operation with a condition that must be satisfied before the operation can proceed.
 * If the condition is not met, the calling thread is suspended (or "blocked") until the condition becomes true.
 * 
 * This pattern is particularly useful in scenarios where a thread needs to wait for a specific state or condition before it can safely perform an operation.
 * For example, a thread may need to wait for data to be available before processing it, or it may need to wait for a resource to be released before acquiring it.
 * The Guarded Suspension pattern typically involves the use of synchronization mechanisms such as locks, condition variables, or semaphores to manage the waiting and notification of threads.
 * Proper implementation of the Guarded Suspension pattern is essential to avoid issues such as deadlocks, race conditions, and spurious wakeups.
 * @see <a href="https://en.wikipedia.org/wiki/Guarded_suspension">Guarded suspension - Wikipedia</a>
 */
public class GuardedSuspension {
    private Object resource;
    private final Object lock = new Object();

    // Method to get the resource, waiting if it's not available
    public Object getResource() throws InterruptedException {
        synchronized (lock) {
            while (resource == null) {
                lock.wait(); // Wait until notified that the resource is available
            }
            return resource;
        }
    }

    // Method to set the resource and notify waiting threads
    public void setResource(Object resource) {
        synchronized (lock) {
            this.resource = resource;
            lock.notifyAll(); // Notify all waiting threads that the resource is now available
        }
    }
}

/**
 * A simple Guarded Queue implementation using the Guarded Suspension pattern.
 * It can be replaced by BlockingQueue from java.util.concurrent package in real applications.
 */
class GuardedQueue {
    private final Queue<Integer> queue = new LinkedList<>();

    public Integer get() {
        synchronized (queue) {
            // use while to avoid spurious wakeup
            while (queue.isEmpty()) {
                try {
                    queue.wait(); // Condition not met, wait
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            return queue.poll();
        }
    }

    public void put(Integer value) {
        synchronized (queue) {
            queue.offer(value);
            queue.notifyAll(); // Condition met, notify waiting threads
        }
    }
}
