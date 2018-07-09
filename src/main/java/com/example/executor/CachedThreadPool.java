package com.example.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CachedThreadPool {

    public static void main(String[] args) {
        /*
            ThreadPoolExecutor can be created with the Executors.newCachedThreadPool() method. This method does not
            receive a number of threads at all. The corePoolSize is actually set to 0, and the maximumPoolSize is set
            to Integer.MAX_VALUE for this instance. The keepAliveTime is 60 seconds for this one.
            These parameter values mean that the cached thread pool may grow without bounds to accommodate any amount
            of submitted tasks. But when the threads are not needed anymore, they will be disposed of after
            60 seconds of inactivity.
         */
        ThreadPoolExecutor cachedThreadPool =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        cachedThreadPool.submit(() -> {
            System.out.println("cachedThreadPool " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return null;
        });
        cachedThreadPool.submit(() -> {
            System.out.println("cachedThreadPool " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return null;
        });
        cachedThreadPool.submit(() -> {
            System.out.println("cachedThreadPool " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return null;
        });
        System.out.println("cachedThreadPool size " + cachedThreadPool.getPoolSize());
        System.out.println("cachedThreadPool queue size " + cachedThreadPool.getQueue().size());
        cachedThreadPool.shutdown();
    }
}
