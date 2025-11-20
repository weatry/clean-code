package com.github.budwing.messy.concurrency;

/**
 * Context Switch Overhead Example.
 * Context switching is the process of storing and restoring the state (context) of a CPU, allowing multiple processes to share a single CPU resource.
 * While context switching is essential for multitasking, excessive context switches can lead to performance degradation due to the overhead involved in saving and loading contexts.
 * In this example, we simulate a scenario where multiple threads are competing for CPU time, leading to increased context switch overhead.
 */
public class ContextSwitchOverhead {

    public static void main(String[] args) {
        int tasks = 1000;
        int iterations = 100000;

        Runnable task = () -> {
            for (int i = 0; i < iterations; i++) {
                // Simulate some work
                double value = Math.sqrt(i);
            }
        };

        Thread[] threads = new Thread[tasks];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
        }

        long startTime = System.nanoTime();

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

        long endTime = System.nanoTime();
        System.out.printf("Total elapsed: %.3f ms%n", (endTime - startTime) / 1_000_000.0);
    }



}
