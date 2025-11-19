package com.github.budwing.messy.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.github.budwing.User;

public class BankService {

    private static BankService instance;

    /**
     * Race condition in singleton
     * @return
     */
    public static BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
        }
        return instance;
    }

    private BankService() {
        // do some initialization
    }

    /**
     * Typical example of race condition.
     * 
     * Race Condition is a behavior where the output is dependent on the sequence or timing of uncontrollable events such as thread scheduling.
     * It becomes a problem when two or more threads access shared data and try to change it at the same time.
     * If the access to the shared data is not properly synchronized, it can lead to inconsistent or incorrect results.
     * @param amount
     */
    public void withdraw(User user, Double amount) {
        // check and act
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            System.out.println(Thread.currentThread().getName() + 
                " withdraw " + amount + " successfully, balance: " + user.getBalance());
        }
    }

    /**
     * Typical example of deadlock
     * Dead Lock occurs when two or more threads are blocked forever, each waiting for the other to release a resource.
     * In this example, if Thread A locks 'from' and Thread B locks 'to' at the same time, they will both wait indefinitely for each other to release the lock.
     * 
     * To avoid deadlock, one common strategy is to always acquire locks in a consistent order.
     * For example, you could use the hash code of the User objects to determine the order of locking.
     */
    public void transfer(User from, User to, Double amount) {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + 
                " locked " + from.getUsername());
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + 
                    " locked " + to.getUsername());
                if (from.getBalance() >= amount) {
                    from.setBalance(from.getBalance() - amount);
                    to.setBalance(to.getBalance() + amount);
                    System.out.println(Thread.currentThread().getName() + 
                        " transfer " + amount + " successfully from " + 
                        from.getUsername() + " to " + to.getUsername());
                }
            }
        }
    }

    /**
     * Example of live lock.
     * Live Lock occurs when two or more threads are actively trying to acquire locks but keep failing and retrying,
     * resulting in a situation where they are not blocked but still cannot make progress.
     * In this example, both threads keep trying to acquire the locks on 'from' and 'to', but if they fail,
     * they back off and retry, leading to a situation where they are both active but unable to complete the transfer.
     * 
     * To avoid live lock, you can implement a back-off strategy with random delays or limit the number of retries.
     */
    public void transferLiveLock(User from, User to, Double amount) {
        while (true) {
            if (from.getLock().tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + 
                        " locked " + from.getUsername());
                    if (to.getLock().tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + 
                                " locked " + to.getUsername());
                            if (from.getBalance() >= amount) {
                                from.setBalance(from.getBalance() - amount);
                                to.setBalance(to.getBalance() + amount);
                                System.out.println(Thread.currentThread().getName() + 
                                    " transfer " + amount + " successfully from " + 
                                    from.getUsername() + " to " + to.getUsername());
                                return;
                            }
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            } else {
                // back off
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Non typical example of deadlock.
     * Here, we synchronize on the user object itself, which can lead to deadlock if the same user is involved in multiple concurrent operations.
     * So it's important to be cautious when synchronizing on objects that might be used in multiple threads.
     * 
     * @param user
     * @param amount
     */
    public void save(User user, Double amount) {
        synchronized (user) {
            user.setBalance(user.getBalance() + amount);
            System.out.println(Thread.currentThread().getName() + 
                " save " + amount + " successfully, balance: " + user.getBalance());
        }
    }

    /**
     * Starvation example.
     * Starvation occurs when a thread is perpetually denied access to resources it needs for execution because other higher-priority threads are continuously given preference.
     * In this example, if there are many high-priority threads continuously trying to withdraw money,
     * a low-priority thread may never get a chance to execute its withdraw method, leading to starvation.
     * 
     * To avoid starvation, it's important to ensure that all threads get a fair chance to execute,
     * which can be achieved by using fair locks or adjusting thread priorities appropriately.
     */
    public void withdrawWithPriority(User user, Double amount, int priority) {
        // Simulate priority by using sleep
        try {
            Thread.sleep(10 * (10 - priority)); // Higher priority threads sleep less
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        withdraw(user, amount);
        System.out.println(Thread.currentThread().getName() + 
            " with priority " + priority + " completed withdrawal.");
    }

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

    /**
     * Context Switch Overhead Example.
     * Context switching is the process of storing and restoring the state (context) of a CPU, allowing multiple processes to share a single CPU resource.
     * While context switching is essential for multitasking, excessive context switches can lead to performance degradation due to the overhead involved in saving and loading contexts.
     * In this example, we simulate a scenario where multiple threads are competing for CPU time, leading to increased context switch overhead.
     */
    public void simulateContextSwitchOverhead() {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                // Simulate some work
                double value = Math.sqrt(i);
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
        }

        long startTime = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken with potential context switch overhead: " + (endTime - startTime) + " ms");
    }

}
