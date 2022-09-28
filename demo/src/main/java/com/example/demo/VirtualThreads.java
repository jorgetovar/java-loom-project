package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VirtualThreads {

    private static final Logger log = LoggerFactory.getLogger(VirtualThreads.class);

    private final ExecutorService executorService;

    public VirtualThreads(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void process(String threadPoolType) throws InterruptedException, ExecutionException {

        try (ExecutorService executor = executorService) {
            List<Task> tasks = IntStream.range(0, 1_000)
                    .mapToObj(Task::new)
                    .collect(Collectors.toList());

            long time = System.currentTimeMillis();

            List<Future<Integer>> futures = executor.invokeAll(tasks);

            long sum = 0;
            for (Future<Integer> future : futures) {
                sum += future.get();
            }

            time = System.currentTimeMillis() - time;
            log.info("Result = {} ThreadPool sum: {} in {} ms", threadPoolType, sum, time);
            executor.shutdown();
        }
    }
}
