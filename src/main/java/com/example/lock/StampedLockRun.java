package com.example.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockRun {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Map<String, String> map = new HashMap<>();

        /*
            StampedLock which also support read and write locks just like in the example above.
            In contrast to ReadWriteLock the locking methods of a StampedLock return a stamp represented by a long value.
            You can use these stamps to either release a lock or to check if the lock is still valid.
         */
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                Thread.sleep(100);
                map.put("foo", "bar");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlockRead(stamp);
            }
        };
        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);
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
