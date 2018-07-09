package com.example.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Exchanger;

public class ExchangerRun {

    public static void main(String[] args) {
        Exchanger<Message> exchanger = new Exchanger<>();
        Thread t1 = new EmployeeJob(exchanger);
        Thread t2 = new ManagerJob(exchanger);
        t1.start();
        t2.start();
    }
}


class Message {
    private String header;
    private String body;

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

class ManagerJob extends Thread {
    private Exchanger<Message> exchanger;
    private Queue<Message> queue = new LinkedList<>();

    public ManagerJob(Exchanger<Message> exchanger) {
        this.exchanger = exchanger;
    }

    public Exchanger<Message> getExchanger() {
        return exchanger;
    }

    public void setExchanger(Exchanger<Message> exchanger) {
        this.exchanger = exchanger;
    }

    public Queue<Message> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String header = Thread.currentThread().getName();
        String body = "I will never raise your salary!!!";
        queue.offer(new Message(header, body));
        try {
            Message exchange = exchanger.exchange(queue.poll());
            System.out.println("Manager received message: " + exchange.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EmployeeJob extends Thread {

    private Exchanger<Message> exchanger;
    private Queue<Message> queue = new LinkedList<>();

    public EmployeeJob(Exchanger<Message> exchanger) {
        this.exchanger = exchanger;
    }

    public Exchanger<Message> getExchanger() {
        return exchanger;
    }

    public void setExchanger(Exchanger<Message> exchanger) {
        this.exchanger = exchanger;
    }

    public Queue<Message> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String header = Thread.currentThread().getName();
        String body = "Employee asks to raise salary!";
        queue.offer(new Message(header, body));
        try {
            Message exchange = exchanger.exchange(queue.poll());
            System.out.println("Employee received message: " + exchange.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
