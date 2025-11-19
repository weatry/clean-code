package com.github.budwing.clean.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankService {
    private int balance = 100;

    /**
     * solution 1: synchronized method
     */
    public synchronized void withdrawSync(int amount) {
        // check and act
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println(Thread.currentThread().getName() + 
                " withdraw " + amount + " successfully, balance: " + balance);
        }
    }

    /**
     * solution 2: ReentrantLock
     */
    private final Lock lock = new ReentrantLock();
    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance = balance - amount;
                System.out.println(Thread.currentThread().getName() + 
                    " withdraw " + amount + " successfully, balance: " + balance);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * solution 3: AtomicInteger with CAS
     */
    public static class  Solution3 {
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
