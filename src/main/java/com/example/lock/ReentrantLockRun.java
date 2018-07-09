package com.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockRun {

    private static final int NUM_INCREMENTS = 100;

    /*
        The class ReentrantLock is a mutual exclusion lock with the same basic behavior as the implicit monitors
        accessed via the synchronized keyword but with extended capabilities.
        As the name suggests this lock implements reentrant characteristics just as implicit monitors.
     */
    private static Lock lock = new ReentrantLock();

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(ReentrantLockRun::increment));
        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(ReentrantLockRun::decrement));

        stop(executor);

        System.out.println(count);
    }


    /*
        A lock is acquired via lock() and released via unlock().
        It's important to wrap your code into a try/finally block to ensure unlocking in case of exceptions.
     */
    private static void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    private static void decrement() {
        lock.lock();
        try {
            count--;
        } finally {
            lock.unlock();
        }
    }

    private static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }
}
