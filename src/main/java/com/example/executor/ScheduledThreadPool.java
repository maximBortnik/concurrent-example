package com.example.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    public static void main(String[] args) {
        /*
            The ScheduledThreadPoolExecutor extends the ThreadPoolExecutor class and also implements the
            ScheduledExecutorService interface with several additional methods:
            - schedule method allows to execute a task once after a specified delay;
            - scheduleAtFixedRate method allows to execute a task after a specified initial delay and then execute it
              repeatedly with a certain period; the period argument is the time measured between the starting times
              of the tasks, so the execution rate is fixed;
            - scheduleWithFixedDelay method is similar to scheduleAtFixedRate in that it repeatedly executes
              the given task, but the specified delay is measured between the end of the previous task and the start
              of the next; the execution rate may vary depending on the time it takes to execute any given task.

            The Executors.newScheduledThreadPool() method is typically used to create a ScheduledThreadPoolExecutor
            with a given corePoolSize, unbounded maximumPoolSize and zero keepAliveTime.
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(() -> {
            System.out.println("scheduledExecutorService " + Thread.currentThread().getName() + " by schedule method");
        }, 500, TimeUnit.MILLISECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("scheduledExecutorService " + Thread.currentThread().getName() + " by scheduleAtFixedRate method");
        }, 1000, 3000, TimeUnit.MILLISECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("scheduledExecutorService " + Thread.currentThread().getName() + " by scheduleWithFixedDelay method");
        }, 1000, 3000, TimeUnit.MILLISECONDS);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Thread.currentThread().interrupt();
            scheduledExecutorService.shutdown();
        }
    }
}
