package com.rishab;

public class Strings {
    public static void main(String[] args) {

        /*
         * String is a sequence of characters, and they are immutable in Java
         * Hence, when we modify a string, a new string is created in memory and the reference to the old string is lost
         * To avoid this, we use the StringBuilder class
         */

        String myString = "This is a string";
        System.out.println("myString: " + myString); // This is a string
        myString = myString + ", and this is more.";
        System.out.println("myString: " + myString); // This is a string, and this is more.
        myString = myString + " I wish I had \u00241000.";
        System.out.println("myString: " + myString); // This is a string, and this is more. I wish I had $1000.

        String numberString = "250.55";
        numberString = numberString + "49.95";
        System.out.println("numberString: " + numberString); // 250.5549.95

        String anotherString = "10";
        int myInt = 50;
        anotherString = anotherString + myInt;
        System.out.println("anotherString: " + anotherString); // 1050

        double doubleNumber = 120.47d;
        anotherString = anotherString + doubleNumber;
        System.out.println("anotherString: " + anotherString); // 1050120.47

    }
}
