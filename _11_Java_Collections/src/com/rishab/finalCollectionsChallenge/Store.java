package com.rishab.finalCollectionsChallenge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class Store {

    private static Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> inventoryByCategory;

    public static void main(String[] args) {

        Store store = new Store();
        store.stockStore();
        store.listInventory();

        store.stockAisles();
        store.listProductsByCategory();

        store.manageStoreCarts();
        store.listProductsByCategory(false, true);

        store.carts.forEach(System.out::println);
        store.abandonCarts();
        store.listProductsByCategory(false, true);
        store.carts.forEach(System.out::println);

    }

    private void manageStoreCarts() {
        Cart cart = new Cart(Cart.CartType.PHYSICAL, 1);
        this.carts.add(cart);

        InventoryItem item = this.inventoryByCategory.get(Category.PRODUCE).get("apple");
        cart.addItem(item, 5);
        cart.addItem(inventoryByCategory.get(Category.PRODUCE).get("banana"), 3);
        cart.addItem(inventoryByCategory.get(Category.BEVERAGE).get("coffee"), 2);
        System.out.println(cart);
        cart.removeItem(inventoryByCategory.get(Category.PRODUCE).get("apple"), 2);
        System.out.println(cart);

        Cart virtualCart = new Cart(Cart.CartType.VIRTUAL, 1);
        this.carts.add(virtualCart);
        virtualCart.addItem(this.inventory.get("L103"), 20);
        virtualCart.addItem(this.inventory.get("B100"), 10);
        System.out.println(virtualCart);

        Cart virtualCart2 = new Cart(Cart.CartType.VIRTUAL, 0);
        this.carts.add(virtualCart2);
        virtualCart2.addItem(this.inventory.get("R777"), 998);
        System.out.println(virtualCart2);

        if (!checkoutCart(virtualCart2)) {
            System.out.println("Unable to checkout cart");
        }

        Cart physicalCart = new Cart(Cart.CartType.PHYSICAL, 0);
        this.carts.add(physicalCart);
        physicalCart.addItem(this.inventoryByCategory.get(Category.BEVERAGE).get("tea"), 1);
        System.out.println(physicalCart);
    }

    private boolean checkoutCart(Cart cart) {
        for (var cartItem : cart.getProducts().entrySet()) {
            var item = this.inventory.get(cartItem.getKey());
            int quantity = cartItem.getValue();
            if (!item.sellItem(quantity)) return false;
        }
        cart.printSalesSlip(this.inventory);
        carts.remove(cart);
        return true;
    }

    private void abandonCarts() {
        int dayOfYear = LocalDate.now().getDayOfYear();
        Cart lastCart = null;
        for (Cart cart : this.carts) {
            if (cart.getCartDate().getDayOfYear() == dayOfYear) {
                break;
            }
            lastCart = cart;
        }
        var oldCarts = this.carts.headSet(lastCart, true);
        Cart abandonedCart = null;
        while ((abandonedCart = oldCarts.pollFirst()) != null) {
            for (String sku : abandonedCart.getProducts().keySet()) {
                InventoryItem item = this.inventory.get(sku);
                item.releaseItem(abandonedCart.getProducts().get(sku));
            }
        }
    }

    private void listProductsByCategory() {
        listProductsByCategory(true, false);
    }

    private void listProductsByCategory(boolean header, boolean detail) {
        this.inventoryByCategory.keySet().forEach(k -> {
            if (header) System.out.println("—".repeat(50) + "\n" + k + "\n" + "—".repeat(50));
            if (!detail) {
                this.inventoryByCategory.get(k).keySet().forEach(System.out::println);
            } else {
                this.inventoryByCategory.get(k).values().forEach(System.out::println);
            }
        });
    }

    private void stockStore() {
        this.inventory = new HashMap<>();
        List<Product> products = new ArrayList<>(List.of(
            new Product("A100", "apple", "local", Category.PRODUCE),
            new Product("B100", "banana", "local", Category.PRODUCE),
            new Product("P100", "pear", "local", Category.PRODUCE),
            new Product("L103", "lemon", "local", Category.PRODUCE),
            new Product("M201", "milk", "farm", Category.DAIRY),
            new Product("Y001", "yogurt", "farm", Category.DAIRY),
            new Product("C333", "cheese", "farm", Category.DAIRY),
            new Product("R777", "rice chex", "Nabisco", Category.CEREAL),
            new Product("G111", "granola", "Nat Valley", Category.CEREAL),
            new Product("BB11", "ground beef", "butcher", Category.MEAT),
            new Product("CC11", "chicken", "butcher", Category.MEAT),
            new Product("BC11", "bacon", "butcher", Category.MEAT),
            new Product("BC77", "coke", "coca cola", Category.BEVERAGE),
            new Product("BC88", "coffee", "value", Category.BEVERAGE),
            new Product("BC99", "tea", "herbal", Category.BEVERAGE)
        ));

        products.forEach(p -> this.inventory.put(
            p.stockKeepingUnit(),
            new InventoryItem(p, random.nextDouble(0, 1.25), 1000, 5)
        ));
    }

    private void stockAisles() {
        this.inventoryByCategory = new EnumMap<>(Category.class);
        for (InventoryItem item : this.inventory.values()) {
            Category category = item.getProduct().category();

            Map<String, InventoryItem> productMap = this.inventoryByCategory.get(category);
            if (productMap == null) {
                productMap = new TreeMap<>();
            }
            productMap.put(item.getProduct().name(), item);
            this.inventoryByCategory.putIfAbsent(category, productMap);
        }
    }

    private void listInventory() {
        System.out.println("—".repeat(50));
        this.inventory.values().forEach(System.out::println);
    }
}
