package com.example.thread;

public class SimpleRunnableThread implements Runnable{
    private String message;

    public SimpleRunnableThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message + " " + Thread.currentThread().getName());
    }
}
