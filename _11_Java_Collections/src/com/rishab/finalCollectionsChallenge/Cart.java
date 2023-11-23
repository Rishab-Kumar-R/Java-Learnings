package com.rishab.finalCollectionsChallenge;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class Cart {
    enum CartType {PHYSICAL, VIRTUAL}

    private static int lastId = 1;
    private int id;
    private LocalDate cartDate;
    private CartType cartType;
    private Map<String, Integer> products; // stockKeepingUnit, quantity

    public Cart(CartType cartType, int days) {
        this.cartType = cartType;
        this.id = lastId++;
        this.cartDate = LocalDate.now().minusDays(days);
        this.products = new HashMap<>();
    }

    public Cart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getCartDate() {
        return cartDate;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void addItem(InventoryItem item, int quantity) {
        this.products.merge(item.getProduct().stockKeepingUnit(), quantity, Integer::sum);

        if (!item.reserveItem(quantity)) {
            System.out.println("Unable to reserve item");
        }
    }

    public void removeItem(InventoryItem item, int quantity) {
        int current = this.products.get(item.getProduct().stockKeepingUnit());
        if (current <= quantity) {
            quantity = current;
            this.products.remove(item.getProduct().stockKeepingUnit());
            System.out.printf("Item [%s] removed from cart%n", item.getProduct().name());
        } else {
            this.products.merge(item.getProduct().stockKeepingUnit(), quantity, (oldValue, newValue) -> oldValue - newValue);
            System.out.printf("Item [%s] quantity reduced by %d%n", item.getProduct().name(), quantity);
        }
        item.releaseItem(quantity);
    }

    public void printSalesSlip(Map<String, InventoryItem> inventoryItem) {
        double total = 0;
        System.out.println("—".repeat(50));
        System.out.println("Thank you for shopping with us!");
        for (var cartItem : this.products.entrySet()) {
            var item = inventoryItem.get(cartItem.getKey());
            int quantity = cartItem.getValue();
            double itemizedPrice = item.getPrice() * quantity;
            total += itemizedPrice;
            System.out.printf("\t%s %-10s (%d)@ $%.2f = $%.2f%n", cartItem.getKey(), item.getProduct().name(), quantity, item.getPrice(), itemizedPrice);
        }
        System.out.println("—".repeat(50));
        System.out.printf("Total: $%.2f%n", total);
        System.out.println("—".repeat(50));
    }

    @Override
    public String toString() {
        return "Cart{" +
            "id=" + id +
            ", cartDate=" + cartDate +
            ", products=" + products +
            '}';
    }
}
