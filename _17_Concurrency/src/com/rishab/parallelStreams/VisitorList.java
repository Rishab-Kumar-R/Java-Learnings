package com.rishab.parallelStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class VisitorList {
    private static final CopyOnWriteArrayList<Person> masterList;

    static {
        masterList = Stream.generate(Person::new)
            .distinct()
            .limit(2500)
            .collect(CopyOnWriteArrayList::new, CopyOnWriteArrayList::add, CopyOnWriteArrayList::addAll);
    }

    private final static ArrayBlockingQueue<Person> newVisitors = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {

        Runnable producer = () -> {
            Person visitor = new Person();
            System.out.println("Queueing " + visitor);

            boolean queued = false;
            try {
                queued = newVisitors.offer(visitor, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e.getMessage() + " - " + visitor);
            }

            if (queued) {
//                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is full, cannot add " + visitor);
                System.out.println("Draining queue and writing to file...");

                List<Person> tempList = new ArrayList<>();
                newVisitors.drainTo(tempList);
                List<String> lines = new ArrayList<>();
                tempList.forEach(customer -> lines.add(customer.toString()));
                lines.add(visitor.toString());

                try {
                    Files.write(Path.of("DrainedQueue.txt"), lines,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable consumer = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Polling queue " + newVisitors.size());

            Person visitor = null;
            try {
//                visitor = newVisitors.poll(5, TimeUnit.SECONDS);
                visitor = newVisitors.take(); // blocks until an element is available
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (visitor != null) {
                System.out.println(threadName + " Visitor: " + visitor);
                if (!masterList.contains(visitor)) {
                    masterList.add(visitor);
                    System.out.println("---> New visitor added to master list: " + visitor);
                }
            }
            System.out.println(threadName + " Queue size: " + newVisitors.size());
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0, 3, TimeUnit.SECONDS);

        ScheduledExecutorService consumerExecutor = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 3; i++) {
            consumerExecutor.scheduleAtFixedRate(consumer, 6, 1, TimeUnit.SECONDS);
        }

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS)) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        producerExecutor.shutdown();

        while (true) {
            try {
                if (!consumerExecutor.awaitTermination(3, TimeUnit.SECONDS)) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        consumerExecutor.shutdown();

    }
}
