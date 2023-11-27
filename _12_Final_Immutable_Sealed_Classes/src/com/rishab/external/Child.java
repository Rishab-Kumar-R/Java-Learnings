package com.rishab.external;

import com.rishab.constructorsProject.Parent;

import java.util.Random;

public class Child extends Parent {
    private final int birthOrder = getBirthOrder();
    private final String birthOrderString;

    {
        if (siblings == 0) {
            birthOrderString = "only";
        } else if (birthOrder == 1) {
            birthOrderString = "first";
        } else if (birthOrder == (siblings + 1)) {
            birthOrderString = "last";
        } else {
            birthOrderString = "middle";
        }
        System.out.println("Child instance initializer block");
        System.out.println("Child birth order: " + birthOrder + " (" + birthOrderString + ")");
    }

    public Child() {
        super("Alex", "02/02/1920", 5);
        System.out.println("Child constructor");
    }

    private final int getBirthOrder() {
        if (siblings == 0) return 1;
        return new Random().nextInt(1, siblings + 2);
    }

    @Override
    public String toString() {
        return super.toString() + " [birthOrder='" + birthOrderString + " child']";
    }
}
