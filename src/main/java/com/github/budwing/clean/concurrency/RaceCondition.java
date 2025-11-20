package com.github.budwing.clean.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Example of race condition.
 * 
 * Race Condition is a behavior where the output is dependent on the sequence or timing of uncontrollable events such as thread scheduling.
 * It becomes a problem when two or more threads access shared data and try to change it at the same time.
 * If the access to the shared data is not properly synchronized, it can lead to inconsistent or incorrect results.
 */
public class RaceCondition {
    private static RaceCondition instance;

    /**
     * Race condition in singleton
     */
    public static synchronized RaceCondition getInstance() {
        // check and act
        if (instance == null) {
            // the condition might be changed by other threads before this thread enter this block
            instance = new RaceCondition();
        }
        return instance;
    }

    private RaceCondition() {
        // do some initialization
    }

    /**
     * Race condition solution 1: synchronized the method
     * Pros: 
     * 1. simple and easy to implement
     * 2. locks are automatically released when the method exits, reducing the risk of programming errors
     * Cons: 
     * 1. may lead to performance bottlenecks under high contention, as only one thread can access the method at a time
     * 2. may cause thread starvation if high-priority threads continuously acquire the lock, preventing lower-priority threads from executing
     * 3. risk of deadlocks if not managed carefully, especially when multiple synchronized methods are involved
     */
    class Solution1 {
        private int balance = 100;

        public synchronized void withdraw(int amount) {
            // check and act
            if (balance >= amount) {
                balance = balance - amount;
                System.out.println(Thread.currentThread().getName() + 
                    " withdraw " + amount + " successfully, balance: " + balance);
            }
        }
    }

    /**
     * Race condition solution 2: ReentrantLock
     * Pros:
     * 1. more flexible than synchronized methods, allowing for features like tryLock() and lockInterruptibly()
     * 2. can improve performance under high contention by allowing multiple threads to access the resource concurrently in certain scenarios
     * Cons:
     * 1. requires explicit lock management, increasing the risk of programming errors such as forgetting to release the lock
     * 2. may lead to deadlocks if not managed carefully, especially when multiple locks are involved
     */
    class Solution2 {
        private int balance = 100;
        private final Lock lock = new ReentrantLock();

        public void withdraw(int amount) throws InterruptedException {
            lock.lockInterruptibly();
            try {
                // check and act
                if (balance >= amount) {
                    balance = balance - amount;
                    System.out.println(Thread.currentThread().getName() + 
                        " withdraw " + amount + " successfully, balance: " + balance);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * solution 3: AtomicInteger with CAS
     * Pros:
     * 1. high performance under low to moderate contention, as it avoids the overhead of locking
     * 2. eliminates the risk of deadlocks since it doesn't use locks
     * Cons:
     * 1. can lead to livelocks under high contention, where threads continuously retry without making progress
     * 2. more complex to implement and understand compared to synchronized methods or locks
     */
    class  Solution3 {
        private AtomicInteger balance = new AtomicInteger(100);
        public void withdraw(int amount) {
            int current;
            do {
                current = balance.get();
                if (current < amount) {
                    System.out.println("Insufficient funds");
                    return;
                }
            } while (!balance.compareAndSet(current, current - amount));
            // CAS operation: only update when the balance is still the current value
        }
            
    }
}
