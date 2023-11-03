package com.rishab;

public class Variables {
    public static void main(String[] args) {

        int myNumber = 88;
        System.out.println("Initial value of myNumber: " + myNumber);

        myNumber = 100;
        System.out.println("New value of myNumber: " + myNumber);

        myNumber = myNumber + 1;
        System.out.println("New value after incrementing myNumber: " + myNumber);

        // Expression: a sequence of variables, operators, and method calls that evaluates to a single value
        int myOtherNumber = (10 + 2) * 10;
        int myThirdNumber = (myNumber * 2) + (myOtherNumber * 2);
        System.out.println("myThirdNumber: " + myThirdNumber);
    }
}
