package com.rishab.runningThreads;

public class Main {
    public static void main(String[] args) {

        System.out.println("Main thread is running...");
        try {
            System.out.println("Main thread will sleep for 1 second...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread " + threadName + " should take 10 dots to run...");
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("\nThread " + threadName + " interrupted...");
                    Thread.currentThread().interrupt(); // re-interrupting the thread
                    return;
                }
            }
            System.out.println("\nThread " + threadName + " has finished running...");
        });

        Thread installThread = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(250);
                    System.out.println("Installation Step " + (i + 1) + " completed...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "InstallThread");

        Thread threadMonitor = new Thread(() -> {
            long now = System.currentTimeMillis();
            while (thread.isAlive()) {
                try {
                    Thread.sleep(1000);

                    if (System.currentTimeMillis() - now > 8000) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread.getName() + " thread is starting...");
        thread.start();
        threadMonitor.start();

        try {
            thread.join(); // waits for the thread to complete before continuing with the main thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!thread.isInterrupted()) {
            installThread.start();
        } else {
            System.out.println("Thread was interrupted before it could complete..." + installThread.getName() + " cannot start...");
        }

//        System.out.println("Main thread is awake now...");
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt(); // manually interrupting the thread

    }
}
