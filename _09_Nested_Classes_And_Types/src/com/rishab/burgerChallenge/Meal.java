package com.rishab.burgerChallenge;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private double price = 5.0;
    private Burger burger;
    private Item drink;
    private Item side;
    private double conversionRate;

    public Meal() {
        this(1.0);
    }

    public Meal(double conversionRate) {
        this.conversionRate = conversionRate;
        this.burger = new Burger("regular");
        this.drink = new Item("coke", "drink", 1.5);
        this.side = new Item("fries", "side", 2.0);
        System.out.println("The drink is " + this.drink.name);
    }

    public double getTotal() {
        double total = this.burger.getPrice() + this.drink.price + this.side.price;
        return Item.getPrice(total, conversionRate);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26s$%.2f".formatted(burger, drink, side, "Total: ", getTotal());
    }

    public void addToppings(String... selectedToppings) {
        this.burger.addToppings(selectedToppings);
    }

    private class Item {
        private String name;
        private String type;
        private double price;

        public Item(String name, String type) {
            this(name, type, type.equalsIgnoreCase("burger") ? Meal.this.price : 0.0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%-10s%-10s$%.2f".formatted(name, type, getPrice(price, conversionRate));
        }

        // JDK-16 gave the ability have static members in a non-static nested class
        private static double getPrice(double price, double rate) {
            return price * rate;
        }
    }

    private class Burger extends Item {
        // From JDK-16 we can use enums in a non-static nested class, and default to static
        private enum Extras {
            AVOCADO, BACON, CHEESE, EGG, JALAPENOS, MUSHROOMS, ONIONS, TOMATO;

            private double getPrice() {
                return switch (this) {
                    case MUSHROOMS, ONIONS -> 0.5;
                    case AVOCADO, BACON, EGG -> 1.0;
                    default -> 0.0;
                };
            }
        }

        private List<Item> toppings = new ArrayList<>();

        Burger(String name) {
            super(name, "burger", 5.0);
        }

        public double getPrice() {
            double total = super.price;
            for (Item topping : toppings) {
                total += topping.price;
            }
            return total;
        }

        private void addToppings(String... selectedToppings) {
            for (String selectedTopping : selectedToppings) {
                try {
                    Extras topping = Extras.valueOf(selectedTopping.toUpperCase());
                    this.toppings.add(new Item(topping.name(), "TOPPING", topping.getPrice()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid topping: " + selectedTopping);
                }

            }
        }

        @Override
        public String toString() {
            StringBuilder itemized = new StringBuilder(super.toString());
            for (Item topping : toppings) {
                itemized.append(topping);
            }
            return itemized.toString();
        }
    }
}
