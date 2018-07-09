package com.example.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchRun {

    public static void main(String[] args) {
        /*
            Let us create task that is going to wait for three threads before it starts
        */
        CountDownLatch latch = new CountDownLatch(3);

        Worker first = new Worker(1000, latch, "WORKER-1");
        Worker second = new Worker(1500, latch, "WORKER-2");
        Worker third = new Worker(2000, latch, "WORKER-3");

        first.start();
        second.start();
        third.start();

        /*
           Main thread will wait until all thread finished
        */
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        /*
            Main thread has finished
        */
        System.out.println(Thread.currentThread().getName() + " has finished");


    }
}

class Worker extends Thread {
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch, String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is working...");
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}