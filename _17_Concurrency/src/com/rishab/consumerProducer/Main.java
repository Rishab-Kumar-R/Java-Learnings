package com.rishab.consumerProducer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageRepository {
    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock();

    public String read() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    while (!hasMessage) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    hasMessage = false;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Couldn't acquire lock in read " + lock);
                hasMessage = false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    public void write(String message) {
        if (lock.tryLock()) {
            try {
                while (hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Couldn't acquire lock in write " + lock);
            hasMessage = true;
        }
        this.message = message;
    }
}

class MessageWriter implements Runnable {
    private MessageRepository outgoingMessage;
    private final String text = """
        Humpty Dumpty sat on a wall,
        Humpty Dumpty had a great fall.
        All the king's horses and all the king's men,
        Couldn't put Humpty together again.
        """;

    public MessageWriter(MessageRepository outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        outgoingMessage.write("DONE");
    }
}

class MessageReader implements Runnable {
    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String latestMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equals("DONE"));
    }
}

public class Main {
    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository), "Reader");
        Thread writer = new Thread(new MessageWriter(messageRepository), "Writer");

        writer.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Exception occurred in writer thread: " + exception.getMessage());
            if (reader.isAlive()) {
                System.out.println("Killing reader thread");
                reader.interrupt();
            }
        });

        reader.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Exception occurred in reader thread: " + exception.getMessage());
            if (writer.isAlive()) {
                System.out.println("Killing writer thread");
                writer.interrupt();
            }
        });

        reader.start();
        writer.start();

    }
}
