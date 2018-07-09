package com.example.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutor {

    public static void main(String[] args) {

        /*
           The Executors helper class contains several methods for creation of pre-configured thread pool instances for you.
           The Executor and ExecutorService interfaces are used to work with different thread pool implementations in Java.
           The Executor interface has a single execute method to submit Runnable instances for execution.
         */
        Executor singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("singleThreadExecutor by Executor " + threadName);
        });
        ((ExecutorService) singleThreadExecutor).shutdown();


        /*
            The ExecutorService interface contains a large number of methods for controlling the progress of the tasks
            and managing the termination of the service. Using this interface, you can submit the tasks for execution
            and also control their execution using the returned Future instance.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            return "singleThreadExecutor by ExecutorService " + threadName;
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
