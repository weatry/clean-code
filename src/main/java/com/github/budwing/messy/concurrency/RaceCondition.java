package com.github.budwing.messy.concurrency;

import com.github.budwing.User;

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
    public static RaceCondition getInstance() {
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

    public void withdraw(User user, Double amount) {
        // check and act
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            System.out.println(Thread.currentThread().getName() + 
                " withdraw " + amount + " successfully, balance: " + user.getBalance());
        }
    }
}
