package com.github.budwing.clean.concurrency.pattern;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Reader-Writer Lock is a concurrency pattern that improves performance in scenarios where read operations
 * are more frequent than write operations.
 * Reader-Writer Lock is a synchronization mechanism that allows concurrent access for read-only operations,
 * while write operations require exclusive access. This pattern is useful in scenarios where reads are more
 * frequent than writes, as it improves performance by allowing multiple readers to access the shared resource
 * simultaneously.
 * 
 * In a Reader-Writer Lock, multiple threads can hold the lock in read mode, but when a thread wants to write,
 * it must acquire the lock in write mode, which blocks all other readers and writers until the write operation
 * is complete.
 * 
 * Proper implementation of Reader-Writer Locks is essential to prevent issues such as starvation, where writers may be perpetually blocked by continuous read operations.
 * Various strategies, such as reader preference, writer preference, or fair scheduling, can be employed to manage access.
 */
public class ReaderWriterLock {
    private String sharedResource = "Initial Data";
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void readOperation() {
        rwLock.readLock().lock();
        try {
            // Perform read operation
            System.out.printf("%s is reading shared resource: %s\n", Thread.currentThread().getName(), sharedResource);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void writeOperation(String newData) {
        rwLock.writeLock().lock();
        try {
            // Perform write operation
            sharedResource = newData;
            System.out.println("Updated shared resource to: " + sharedResource);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReaderWriterLock dataStore = new ReaderWriterLock();

        // create reader threads
         for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    dataStore.readOperation();
                    try {
                        Thread.sleep(150); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Reader-" + i).start();
        }
        
        // create writer threads
        for (int i = 1; i <= 2; i++) {
            final int writerId = i;
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    dataStore.writeOperation("Data from Writer-" + writerId + " at " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000); // writers write less frequently
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Writer-" + i).start();
        }
        
        // Let the threads run for a while
        try {
            Thread.sleep(10000);
            System.exit(0); // For simplicity, exit directly
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
