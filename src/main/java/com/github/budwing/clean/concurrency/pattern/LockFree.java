package com.github.budwing.clean.concurrency.pattern;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lock-Free is a concurrency pattern that allows multiple threads to access shared data without using traditional locking mechanisms,
 * such as mutexes or semaphores. Instead, lock-free algorithms rely on atomic operations and non-blocking synchronization techniques to ensure data consistency and thread safety.
 * Lock-Free programming aims to minimize contention and improve performance in multi-threaded environments by allowing threads to proceed without waiting for locks to be released.
 * Common techniques used in lock-free programming include Compare-And-Swap (CAS) operations, atomic variables, and memory barriers.
 * This pattern is particularly useful in scenarios where high throughput and low latency are critical, such as in real-time systems or high-performance computing applications.
 * However, lock-free programming can be complex and challenging to implement correctly, as it requires careful consideration of memory visibility, ordering, and potential race conditions.
 * @see <a href="https://en.wikipedia.org/wiki/Lock-free_and_wait-free_algorithms">Lock-free and wait-free algorithms - Wikipedia</a>
 */
public class LockFree {
    public class CASBasedCounter {
        private AtomicInteger value = new AtomicInteger(0);
        
        // CAS-based increment
        public void increment() {
            int current;
            do {
                current = value.get();       // read current value    
                // try to update to current + 1
            } while (!value.compareAndSet(current, current + 1));
        }
        
        // more complex CAS operation: multiply by 2 if even
        public boolean multiplyIfEven() {
            int current;
            do {
                current = value.get();
                if (current % 2 != 0) {
                    return false; // not even, do not update
                }
                // condition met, try to update to current * 2
            } while (!value.compareAndSet(current, current * 2));
            return true;
        }
    }

    public class AtomicCounter {
        private AtomicInteger value = new AtomicInteger(0);
        
        // Atomic increment
        public void increment() {
            value.incrementAndGet();
        }
        
        // Atomic get
        public int get() {
            return value.get();
        }
    }

    public class VersionOptimisticLock {
        // an entity with versioning
        private class Entity {
            int id;
            String data;
            int version;
        }

        public Entity getEntity(int id) {
            // get entity from storage
            return new Entity(); // Placeholder
        }
        
        // update with version check
        public boolean updateWithVersion(int id, String newData, int expectedVersion) {
            Entity entity = getEntity(id);
            synchronized(entity) { // if it's database updating, this is not required
                if (entity.version != expectedVersion) {
                    return false; // version conflict
                }
                // update data and version
                entity.data = newData;
                entity.version++;
                return true;
            }
        }
    }

    public class TimestampOptimisticLock {
        private volatile long last_modified_timestamp = System.currentTimeMillis();
        private String data;
        
        public boolean updateData(String newData, long clientTimestamp) {
            synchronized (this) {
                if (this.last_modified_timestamp != clientTimestamp) {
                    return false; // timestamp not matching
                }
                this.data = newData;
                this.last_modified_timestamp = System.currentTimeMillis();
                return true;
            }
        }
    }
}