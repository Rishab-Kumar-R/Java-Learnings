package com.rishab.consumerProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

record Order(long orderId, String item, int quantity) {
}

class ShoeWareHouse {
    private List<Order> shippingItems;
    public final static String[] PRODUCT_LIST = {"Nike", "Adidas", "Puma", "Reebok", "Fila", "Skechers", "Vans", "Converse", "Jordan"};

    public ShoeWareHouse() {
        this.shippingItems = new ArrayList<>();
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
        System.out.println("Order received: " + item);
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
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                wareHouse.receiveOrder(new Order(
                    random.nextLong(1000000, 9999999),
                    ShoeWareHouse.PRODUCT_LIST[random.nextInt(0, ShoeWareHouse.PRODUCT_LIST.length)],
                    random.nextInt(1, 4)
                ));
            }
        });
        producerThread.start();

        for (int i = 0; i < 2; i++) {
            Thread consumerThread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    Order item = wareHouse.fulfillOrder();
                }
            });
            consumerThread.start();
        }

    }
}
