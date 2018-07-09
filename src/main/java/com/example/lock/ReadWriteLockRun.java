package com.example.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockRun {

    public static void main(String[] args) {
        ReadWriteArrayList<String> readWriteArrayList = new ReadWriteArrayList<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                readWriteArrayList.write(Thread.currentThread().getName());
            }).start();

            new Thread(() -> {
                readWriteArrayList.read(0);
            }).start();
        }
    }
}

class ReadWriteArrayList<T> {
    private List<T> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    public void write(T object) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " write to list");
            list.add(object);
        } finally {
            writeLock.unlock();
        }
    }

    public T read(int val) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read from list");
            return list.get(val);
        } finally {
            readLock.unlock();
        }
    }
}
