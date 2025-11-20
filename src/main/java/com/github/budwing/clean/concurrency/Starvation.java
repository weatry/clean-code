package com.github.budwing.clean.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.github.budwing.User;

/**
 * Starvation example.
 * Starvation occurs when a thread is perpetually denied access to resources it needs for execution because other higher-priority threads are continuously given preference.
 * In this example, if there are many high-priority threads continuously trying to withdraw money,
 * a low-priority thread may never get a chance to execute its withdraw method, leading to starvation.
 * 
 * To avoid starvation, it's important to ensure that all threads get a fair chance to execute,
 * which can be achieved by using fair locks or adjusting thread priorities appropriately.
 */
public class Starvation {
    /**
     * One way to prevent starvation is to use fair locks.
     * A fair lock ensures that threads acquire locks in the order they requested them, preventing any thread from being perpetually denied access.
     * This can help ensure that all threads get a chance to execute their critical sections.
     */
    public void withdrawWithFairLock(User user, Double amount) {
        Lock fairLock = new ReentrantLock(true); // fair lock
        fairLock.lock();
        try {
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                System.out.println(Thread.currentThread().getName() + 
                    " withdraw " + amount + " successfully, balance: " + user.getBalance());
            }
        } finally {
            fairLock.unlock();
        }
    }

    /**
     * Another way to prevent starvation is to use timeouts when trying to acquire locks.
     * This way, if a thread cannot acquire a lock within a certain time frame, it can back off and retry later,
     * giving other threads a chance to execute.
     */
    public void withdrawWithTimeout(User user, Double amount) {
        Lock lock = new ReentrantLock();
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    if (user.getBalance() >= amount) {
                        user.setBalance(user.getBalance() - amount);
                        System.out.println(Thread.currentThread().getName() + 
                            " withdraw " + amount + " successfully, balance: " + user.getBalance());
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + 
                    " could not acquire lock, backing off to prevent starvation.");
                fallbackWithdraw(user, amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fallbackWithdraw(User user, Double amount) {
        // Fallback mechanism: if unable to withdraw, log the attempt
        System.out.println(Thread.currentThread().getName() + 
            " fallback: unable to withdraw " + amount + " for user " + user.getUsername());
    }
}
