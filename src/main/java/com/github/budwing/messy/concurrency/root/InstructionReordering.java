package com.github.budwing.messy.concurrency.root;

/**
 * Instruction Reordering is a phenomenon in concurrent programming where the order of instructions
 * executed by a CPU or compiler may differ from the order specified in the source code. This can
 * lead to unexpected behaviors in multi-threaded applications if proper synchronization mechanisms
 * are not used. 
 */
public class InstructionReordering {
    /**
     * Without proper synchronization, it's possible for both x and y to be 0 after both threads
     * have executed, due to instruction reordering. This can lead to unexpected results in concurrent
     * programs.
     */
    public static class OutOfOrderExample {
        private int x = 0;
        private int y = 0;
        private int a = 0;
        private int b = 0;

        public void thread1() {
            a = 1;      // Step 1
            x = b;      // Step 2
        }

        public void thread2() {
            b = 1;      // Step 3
            y = a;      // Step 4
        }

        
    }

    /**
     * Another example demonstrating instruction reordering.
     * Here, the writer method sets data before flag, but due to reordering,
     * the reader method might see flag set before data is updated.
     */
    public static class OutOfOrderAnotherExample {
        private int flag = 0;
        private int data = 0;

        public void writer() {
            data = 42;      // Step 1
            flag = 1;      // Step 2
        }

        public void reader() {
            if (flag == 1) { // Step 3
                System.out.println(data); // Step 4
            }
        }
    }

    /**
     * Example of Loop Unrolling optimization technique.
     * This is compiler-level optimization but may cause issues in concurrent programming if not handled properly.
     */
    public class LoopUnrollingExample {
        /**
         * Loop unrolling is an optimization technique that involves expanding the loop body to reduce
         * the overhead of loop control and increase the program's performance. This can lead to faster
         * execution by decreasing the number of iterations and branching instructions.
         */
        public void originalLoop(int[] array) {
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] * 2;
            }
        }

        public void unrolledLoop(int[] array) {
            int i = 0;
            int length = array.length;
            int limit = length - (length % 4); // Process in chunks of 4

            // Unrolled loop
            for (; i < limit; i += 4) {
                array[i] = array[i] * 2;
                array[i + 1] = array[i + 1] * 2;
                array[i + 2] = array[i + 2] * 2;
                array[i + 3] = array[i + 3] * 2;
            }

            // Handle remaining elements
            for (; i < length; i++) {
                array[i] = array[i] * 2;
            }
        }
    }
}
