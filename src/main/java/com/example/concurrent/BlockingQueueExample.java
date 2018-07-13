package com.example.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    /*
        java.util.concurrent.BlockingQueue is a java Queue that support operations that wait for the queue
        to become non-empty when retrieving and removing an element, and wait for space
        to become available in the queue when adding an element.

        BlockingQueue doesn’t accept null values and throw NullPointerException
        if you try to store null value in the queue.

        Java BlockingQueue implementations are thread-safe. All queuing methods are atomic in nature
        and use internal locks or other forms of concurrency control.
     */

    public static void main(String[] args) {

        /*
        add() – returns true if insertion was successful, otherwise throws an IllegalStateException
        put() – inserts the specified element into a queue, waiting for a free slot if necessary
        offer() – returns true if insertion was successful, otherwise false
        offer(E e, long timeout, TimeUnit unit) – tries to insert element into a queue
            and waits for an available slot within a specified timeout

        take() – waits for a head element of a queue and removes it. If the queue is empty,
            it blocks and waits for an element to become available
        poll(long timeout, TimeUnit unit) – retrieves and removes the head of the queue, waiting up to the specified
            wait time if necessary for an element to become available. Returns null after a timeout
         */
        BlockingQueue<Character> bq = new ArrayBlockingQueue<>(3);

        Runnable producer = () -> {
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                try {
                    bq.put(ch);
                    System.out.println(ch + " was putted into queue.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getCause().toString());
                }
            }

        };

        Runnable consumer = () -> {
            char ch = '\0';
            do {
                try {
                    ch = bq.take();
                    System.out.println(ch + " was taken from queue.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getCause().toString());
                }
            } while (ch != 'Z');
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
