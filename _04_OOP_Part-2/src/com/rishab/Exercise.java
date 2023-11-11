package com.rishab;

public class Exercise {
    public static void main(String[] args) {

        MealOrder regularMeal = new MealOrder();
        regularMeal.addBurgerToppings("BACON", "CHEESE", "MAYO");
        regularMeal.setDrinkSize("LARGE");
        regularMeal.printItemizedList();

        System.out.println();

        MealOrder anotherMeal = new MealOrder("chicken", "sprite", "nuggets");
        anotherMeal.addBurgerToppings("LETTUCE", "TOMATO", "MAYO");
        anotherMeal.setDrinkSize("SMALL");
        anotherMeal.printItemizedList();

        System.out.println();

        MealOrder deluxeMeal = new MealOrder("deluxe", "sprite", "chili cheese fries");
        deluxeMeal.addBurgerToppings("AVOCADO", "BACON", "LETTUCE", "TOMATO", "CHEESE");
        deluxeMeal.setDrinkSize("SMALL");
        deluxeMeal.printItemizedList();

    }
}

class Item {
    private final String type;
    private final String name;
    private final double price;
    private String size = "MEDIUM";

    public Item(String type, String name, double price) {
        this.type = type.toUpperCase();
        this.name = name.toUpperCase();
        this.price = price;
    }

    public String getName() {
        if (type.equals("SIDE") || type.equals("DRINK")) {
            return this.name + " (" + this.size + ")";
        }
        return this.name;
    }

    public double getBasePrice() {
        return this.price;
    }

    public double getAdjustedPrice() {
        return switch (this.size) {
            case "SMALL" -> getBasePrice() - 0.5;
            case "LARGE" -> getBasePrice() + 1;
            default -> getBasePrice();
        };
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static void printItem(String name, double price) {
        System.out.printf("%-30s: $%.2f%n", name, price);
    }

    public void printItem() {
        printItem(this.getName(), this.getAdjustedPrice());
    }
}

class Burger extends Item {
    private Item extra1;
    private Item extra2;
    private Item extra3;

    public Burger(String name, double price) {
        super("Burger", name, price);
    }

    @Override
    public String getName() {
        return super.getName() + " BURGER";
    }

    @Override
    public double getAdjustedPrice() {
        return getBasePrice() +
            ((this.extra1 == null) ? 0 : this.extra1.getAdjustedPrice()) +
            ((this.extra2 == null) ? 0 : this.extra2.getAdjustedPrice()) +
            ((this.extra3 == null) ? 0 : this.extra3.getAdjustedPrice());
    }

    public double getExtraPrice(String toppingName) {
        return switch (toppingName.toUpperCase()) {
            case "AVOCADO", "CHEESE" -> 1.0;
            case "BACON", "HAM", "SALAMI" -> 1.5;
            default -> 0.0;
        };
    }

    public void addToppings(String extra1, String extra2, String extra3) {
        this.extra1 = new Item("TOPPING", extra1, getExtraPrice(extra1));
        this.extra2 = new Item("TOPPING", extra2, getExtraPrice(extra2));
        this.extra3 = new Item("TOPPING", extra3, getExtraPrice(extra3));
    }

    public void printItemizedList() {
        printItem("BASE BURGER", getBasePrice());
        if (this.extra1 != null) {
            this.extra1.printItem();
        }
        if (this.extra2 != null) {
            this.extra2.printItem();
        }
        if (this.extra3 != null) {
            this.extra3.printItem();
        }
    }

    @Override
    public void printItem() {
        printItemizedList();
        System.out.println("-".repeat(38));
        super.printItem();
    }
}

class MealOrder {
    private final Burger burger;
    private final Item drink;
    private final Item side;

    public MealOrder() {
        this("regular", "coke", "fries");
    }

    public MealOrder(String burgerType, String drinkType, String sideType) {
        if (burgerType.equalsIgnoreCase("deluxe")) {
            this.burger = new DeluxeBurger(burgerType, 8.5);
        } else {
            this.burger = new Burger(burgerType, 4.00);
        }
        this.drink = new Item("drink", drinkType, 1.00);
        this.side = new Item("side", sideType, 1.50);
    }

    public double getTotalPrice() {
        if (burger instanceof DeluxeBurger) {
            return this.burger.getAdjustedPrice();
        }
        return this.burger.getAdjustedPrice() + this.drink.getAdjustedPrice() + this.side.getAdjustedPrice();
    }

    public void printItemizedList() {
        this.burger.printItem();
        if (burger instanceof DeluxeBurger) {
            Item.printItem(drink.getName(), 0);
            Item.printItem(side.getName(), 0);
        } else {
            this.drink.printItem();
            this.side.printItem();
        }
        System.out.println("-".repeat(38));
        Item.printItem("TOTAL", getTotalPrice());
    }

    public void addBurgerToppings(String extra1, String extra2, String extra3) {
        this.burger.addToppings(extra1, extra2, extra3);
    }

    public void addBurgerToppings(String extra1, String extra2, String extra3, String extra4, String extra5) {
        if (this.burger instanceof DeluxeBurger db) {
            db.addToppings(extra1, extra2, extra3, extra4, extra5);
        } else {
            this.burger.addToppings(extra1, extra2, extra3);
        }
    }

    public void setDrinkSize(String size) {
        this.drink.setSize(size);
    }
}

class DeluxeBurger extends Burger {
    Item deluxe1;
    Item deluxe2;

    public DeluxeBurger(String name, double price) {
        super(name, price);
    }

    public void addToppings(String extra1, String extra2, String extra3, String extra4, String extra5) {
        super.addToppings(extra1, extra2, extra3);
        this.deluxe1 = new Item("TOPPING", extra4, 0);
        this.deluxe2 = new Item("TOPPING", extra5, 0);
    }

    @Override
    public void printItemizedList() {
        super.printItemizedList();
        if (this.deluxe1 != null) {
            this.deluxe1.printItem();
        }
        if (this.deluxe2 != null) {
            this.deluxe2.printItem();
        }
    }

    @Override
    public double getExtraPrice(String toppingName) {
        return 0;
    }
}
