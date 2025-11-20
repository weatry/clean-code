package com.github.budwing.messy.concurrency;

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
    public void withdraw(User user, Double amount, int priority) {
        Thread.currentThread().setPriority(priority);
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                System.out.println(Thread.currentThread().getName() + 
                    " withdraw " + amount + " successfully, balance: " + user.getBalance());
            }
        } finally {
            lock.unlock();
        }
    }
}
