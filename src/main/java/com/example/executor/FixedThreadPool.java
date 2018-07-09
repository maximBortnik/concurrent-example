package com.example.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FixedThreadPool {

    public static void main(String[] args) {
         /*
            ThreadPoolExecutor with a fixed thread count of 2. This means that if the amount of simultaneously running
            tasks is less or equal to two at all times, then they get executed right away.
            Otherwise some of these tasks may be put into a queue to wait for their turn.
         */
        ThreadPoolExecutor threadPoolExecutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        threadPoolExecutor.submit(() -> {
            System.out.println("threadPoolExecutor " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return null;
        });
        threadPoolExecutor.submit(() -> {
            System.out.println("threadPoolExecutor " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return null;
        });
        threadPoolExecutor.submit(() -> {
            System.out.println("threadPoolExecutor " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return null;
        });
        System.out.println("threadPoolExecutor size " + threadPoolExecutor.getPoolSize());
        System.out.println("threadPoolExecutor queue size " + threadPoolExecutor.getQueue().size());
        threadPoolExecutor.shutdown();
    }
}
