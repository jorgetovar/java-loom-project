package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ExecutorService loom = Executors.newVirtualThreadPerTaskExecutor();
        ExecutorService classic = Executors.newFixedThreadPool(100);
        VirtualThreads virtualThreads = new VirtualThreads(classic);
        virtualThreads.process("classic");
        virtualThreads = new VirtualThreads(loom);
        virtualThreads.process("loom");
    }

}
