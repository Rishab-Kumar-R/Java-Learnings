package com.rishab.runningThreads;

class OddThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            System.out.println("Odd Thread: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Odd Thread interrupted...");
                break;
            }
        }
    }
}

class EvenRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println("Even Runnable: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Even Runnable interrupted...");
                break;
            }
        }
    }
}

public class Challenge {
    public static void main(String[] args) {

        OddThread oddThread = new OddThread();

        Runnable runnable = () -> {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("Runnable: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Runnable interrupted...");
                    break;
                }
            }
        };

        Thread evenThread = new Thread(runnable);

        oddThread.start();
        evenThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        oddThread.interrupt();

    }
}
