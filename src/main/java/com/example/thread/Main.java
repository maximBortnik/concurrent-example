package com.example.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        Thread thread = new SimpleThread("SimpleThread executed using Thread");
        thread.start();



        Runnable simpleRunnable = new SimpleRunnableThread("SimpleThread executed using Runnable");
        new Thread(simpleRunnable).start();



        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("SimpleThread executed using anonymous class : " + threadName);
        }).start();



        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> factorial = executorService.submit(new FactorialTask(5));
        try {
            while(!factorial.isDone()) {
                System.out.println("Calculating factorial...");
            }
            System.out.println("factorial of 5 is " + factorial.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }



        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}
