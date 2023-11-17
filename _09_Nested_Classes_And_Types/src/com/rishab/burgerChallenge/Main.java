package com.rishab.burgerChallenge;

public class Main {
    public static void main(String[] args) {

        Meal regularMeal = new Meal();
        regularMeal.addToppings("CHEESE", "EGG", "ONIONS", "LETTUCE");
        System.out.println(regularMeal + "\n");

        Meal USRegularMeal = new Meal(0.8);
        System.out.println(USRegularMeal);

    }
}
