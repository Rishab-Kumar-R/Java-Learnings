package com.rishab.executors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

record Order(long orderId, String item, int quantity) {
}

class ShoeWareHouse {
    private List<Order> shippingItems;
    private final ExecutorService fulfillmentService;
    public final static String[] PRODUCT_LIST = {"Nike", "Adidas", "Puma", "Reebok", "Fila", "Skechers", "Vans", "Converse", "Jordan"};

    public ShoeWareHouse() {
        this.shippingItems = new ArrayList<>();
        fulfillmentService = Executors.newFixedThreadPool(3);
    }

    public void shutDown() {
        fulfillmentService.shutdown();
    }

    public synchronized void receiveOrder(Order item) {
        while (shippingItems.size() > 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        shippingItems.add(item);
        System.out.println(Thread.currentThread().getName() + " Order received: " + item);
        fulfillmentService.submit(this::fulfillOrder);
        notifyAll();
    }

    public synchronized Order fulfillOrder() {
        while (shippingItems.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order item = shippingItems.removeFirst();
        System.out.println(Thread.currentThread().getName() + " Order fulfilled: " + item);
        notifyAll();
        return item;
    }
}

public class Challenge {
    private static final Random random = new Random();

    public static void main(String[] args) {

        ShoeWareHouse wareHouse = new ShoeWareHouse();
        ExecutorService orderingService = Executors.newCachedThreadPool();
        Callable<Order> orderingTask = () -> {
            Order newOrder = generateOrder();
            try {
                Thread.sleep(random.nextInt(500, 5000));
                wareHouse.receiveOrder(newOrder);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return newOrder;
        };

//        List<Callable<Order>> tasks = Collections.nCopies(15, orderingTask);
//        try {
//            orderingService.invokeAll(tasks);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        try {
            Thread.sleep(random.nextInt(500, 2000));
            for (int i = 0; i < 15; i++) {
                orderingService.submit(() -> wareHouse.receiveOrder(generateOrder()));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        orderingService.shutdown();
        try {
            orderingService.awaitTermination(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wareHouse.shutDown();

    }

    private static Order generateOrder() {
        return new Order(
            random.nextLong(1000000, 9999999),
            ShoeWareHouse.PRODUCT_LIST[random.nextInt(0, ShoeWareHouse.PRODUCT_LIST.length)],
            random.nextInt(1, 4)
        );
    }
}
