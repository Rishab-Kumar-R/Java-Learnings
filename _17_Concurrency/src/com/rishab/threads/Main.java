package com.rishab.threads;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        var currentThread = Thread.currentThread();
        System.out.println(currentThread.getClass().getName());

        System.out.println(currentThread); // Thread[#1,main,5,main] -> [Thread ID, Thread Name, Thread Priority, Thread Group]
        printThreadState(currentThread);

        currentThread.setName("Main Thread");
        currentThread.setPriority(Thread.MAX_PRIORITY);
        printThreadState(currentThread);

        CustomThread customThread = new CustomThread();
        customThread.start();

        Runnable myRunnable = () -> {
            for (int i = 1; i <= 8; i++) {
                System.out.println("Hello from " + Thread.currentThread().getName() + " " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    System.out.println("Thread Interrupted");
                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();

        for (int i = 1; i <= 3; i++) {
            System.out.println("Hello from " + currentThread.getName() + " " + i);
            try {
                TimeUnit.SECONDS.sleep(1); // Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
        }

    }

    public static void printThreadState(Thread thread) {
        System.out.println("---------------------");
        System.out.println("Thread ID: " + thread.getId());
        System.out.println("Thread Name: " + thread.getName());
        System.out.println("Thread Priority: " + thread.getPriority());
        System.out.println("Thread State: " + thread.getState());
        System.out.println("Thread Group: " + thread.getThreadGroup());
        System.out.println("Thread is Alive: " + thread.isAlive());
        System.out.println("---------------------");
    }
}
