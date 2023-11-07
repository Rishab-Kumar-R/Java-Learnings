package com.rishab;

public class Scoping {
    public static void main(String[] args) {
        int varOne = 1;
        System.out.println("varOne is " + varOne);
        {
            int varTwo = 2;
            System.out.println("varOne is " + varOne);
            System.out.println("varTwo is " + varTwo);
        }
        System.out.println("varOne is " + varOne);
        // System.out.println("varTwo is " + varTwo); // varTwo is out of scope
    }
}
