package com.rishab.parallelProcesses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class RecursiveSumTask extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    private final int division;

    public RecursiveSumTask(long[] numbers, int start, int end, int division) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.division = division;
    }

    @Override
    protected Long compute() {
        if ((end - start) <= (numbers.length / division)) {
            System.out.println(start + " : " + end);

            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = start + (end - start) / 2;
            RecursiveSumTask left = new RecursiveSumTask(numbers, start, mid, division);
            RecursiveSumTask right = new RecursiveSumTask(numbers, mid, end, division);
            left.fork(); // fork() method submits the task to the pool
            right.fork();
            return left.join() + right.join(); // here join() method is similar to get() method of Future
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {

        int numbersLength = 100_000;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();

        long sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);

//        ForkJoinPool threadPool = (ForkJoinPool) Executors.newWorkStealingPool(4);
        ForkJoinPool threadPool = ForkJoinPool.commonPool();
        List<Callable<Long>> tasks = new ArrayList<>();
        int taskNo = 10;
        int splitCount = numbersLength / taskNo;

        for (int i = 0; i < taskNo; i++) {
            int start = i * splitCount;
            int end = start + splitCount;
            tasks.add(() -> {
                long taskSum = 0;
                for (int j = start; j < end; j++) {
                    taskSum += (long) numbers[j];
                }
                return taskSum;
            });
        }

        List<Future<Long>> futures = threadPool.invokeAll(tasks);

        System.out.println("CPUs: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Parallelism = " + threadPool.getParallelism());
        System.out.println("Pool size = " + threadPool.getPoolSize());
        System.out.println("Steal count = " + threadPool.getStealCount());

        long taskSum = 0;
        for (Future<Long> future : futures) {
            taskSum += future.get();
        }

        System.out.println("Thread Pool sum = " + taskSum);

        RecursiveTask<Long> task = new RecursiveSumTask(numbers, 0, numbers.length, 8);
        long forkJoinSum = threadPool.invoke(task);
        System.out.println("Fork Join sum = " + forkJoinSum);

        threadPool.shutdown();
        System.out.println(threadPool.getClass().getName());

    }
}
