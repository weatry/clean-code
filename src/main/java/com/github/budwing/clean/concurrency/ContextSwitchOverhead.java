package com.github.budwing.clean.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Context Switch Overhead Example.
 * Context switching is the process of storing and restoring the state (context) of a CPU, allowing multiple processes to share a single CPU resource.
 * While context switching is essential for multitasking, excessive context switches can lead to performance degradation due to the overhead involved in saving and loading contexts.
 * In this example, we simulate a scenario where multiple threads are competing for CPU time, leading to increased context switch overhead.
 */
public class ContextSwitchOverhead {
    private static ExecutorService executor;

    /**
     * Context switch overhead can be mitigated by using a thread pool.
     * By reusing a fixed number of threads, we can reduce the number of context switches required to handle multiple tasks.
     */
    public static void main(String[] args) throws InterruptedException {
        int tasks = 1000;
        int iterations = 100000;
        int poolSize = Runtime.getRuntime().availableProcessors(); 
        executor = Executors.newFixedThreadPool(poolSize);

        long start = System.nanoTime();
        for (int i = 0; i < tasks; i++) {
            executor.execute(() -> {
                for (int j = 0; j < iterations; j++) {
                    // Simulate some work
                    double value = Math.sqrt(j);
                }
            });
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(5, TimeUnit.MINUTES);
        if (!finished) {
            System.err.println("Timeout waiting for tasks");
            executor.shutdownNow();
        }

        long end = System.nanoTime();
        System.out.printf("Total elapsed: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}
