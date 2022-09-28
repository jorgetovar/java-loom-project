package com.example.demo;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {
    private final int number;

    public Task(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws InterruptedException {
        Thread.sleep(1000);
        return number;
    }
}
