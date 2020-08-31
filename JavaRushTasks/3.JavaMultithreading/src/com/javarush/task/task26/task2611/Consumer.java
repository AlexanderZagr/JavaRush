package com.javarush.task.task26.task2611;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer implements Runnable {
    private ConcurrentHashMap<String, String> map;
    private BlockingQueue queue;

    public Consumer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted()) {
            if (!map.isEmpty()) {
                for (String key : map.keySet()) {
                    System.out.println(map.remove(key));
                }
            }
        }
    }
}