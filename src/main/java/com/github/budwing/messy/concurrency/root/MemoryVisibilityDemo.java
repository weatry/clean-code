package com.github.budwing.messy.concurrency.root;

public class MemoryVisibilityDemo {

    private static boolean stop = false;
    private static int counter = 0;

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            System.out.println("Worker thread started.");
            while (!stop) {
                counter++;
            }
            System.out.println("Worker thread stopping.");
        });
        worker.start();

        try {
            Thread.sleep(1000); // Let the worker run for a second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread setting stop to true.");
        stop = true;

        System.out.println("counter: " + counter);
    }

}
