package com.github.budwing.clean.concurrency.pattern;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producer-Consumer(PC) is a classic concurrency pattern that involves two types of threads: producers and consumers.
 * The producer thread is responsible for generating data, while the consumer thread processes that data. 
 * A shared buffer or queue is typically used to hold the data produced by the producer until it is consumed by the consumer.
 * 
 * This pattern helps to decouple the production of data from its consumption, allowing both processes to operate at their own pace.
 * Proper synchronization mechanisms, such as locks, semaphores, or blocking queues, are essential to ensure thread safety and prevent issues like race conditions and deadlocks.
 * @see <a href="https://en.wikipedia.org/wiki/Producer%E2%80%93consumer_problem">Producer–consumer problem - Wikipedia</a>
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        System.out.println("Run Producer-Consumer using wait/notify(Y/N)?");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            if ("Y".equalsIgnoreCase(input)) {
                pcByWaitAndNotifyExample();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Run Producer-Consumer using BlockingQueue(Y/N)?");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            if ("Y".equalsIgnoreCase(input)) {
                pcByBlockingQueueExample();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pcByWaitAndNotifyExample() {
        PCByWaitAndNotify pcExample = new PCByWaitAndNotify();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    pcExample.produce(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = pcExample.consume();
                    System.out.println("Consumed: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void pcByBlockingQueueExample() {
        PCByBlockingQueue pcExample = new PCByBlockingQueue();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    pcExample.produce(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = pcExample.consume();
                    System.out.println("Consumed: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * Implementation of Producer-Consumer using wait/notify mechanism.
 * This approach requires careful handling of synchronization to avoid issues like deadlocks and race conditions.
 * 
 */
class PCByWaitAndNotify {
    private static final int BUFFER_SIZE = 5;
    private final int[] buffer = new int[BUFFER_SIZE];
    private int count = 0;

    public synchronized void produce(int value) throws InterruptedException {
        while (count == BUFFER_SIZE) {
            wait(); // Buffer is full, wait for consumer to consume
        }
        buffer[count++] = value;
        notifyAll(); // Notify consumer that an item has been produced
    }

    public synchronized int consume() throws InterruptedException {
        while (count == 0) {
            wait(); // Buffer is empty, wait for producer to produce
        }
        int value = buffer[--count];
        notifyAll(); // Notify producer that an item has been consumed
        return value;
    }
}

/**
 * Implementation of Producer-Consumer using BlockingQueue.
 * This approach leverages the built-in thread-safety and blocking capabilities of BlockingQueue,
 * simplifying the implementation and reducing the risk of concurrency issues.
 * 
 */
class PCByBlockingQueue {
    private static final int BUFFER_SIZE = 5;
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(BUFFER_SIZE);

    public void produce(int value) throws InterruptedException {
        queue.put(value); // Blocks if the queue is full
    }

    public int consume() throws InterruptedException {
        return queue.take(); // Blocks if the queue is empty
    }
}