package com.rishab;

public class PrimitiveTypes {
    public static void main(String[] args) {
        // Primitive types: byte, short, int, long, float, double, char, boolean
        // byte: 8 bits
        // short: 16 bits
        // int: 32 bits
        // long: 64 bits
        // float: 32 bits
        // double: 64 bits
        // char: 16 bits
        // boolean: 1 bit

        // int
        int myValue = 10000;
        int myMinIntValue = Integer.MIN_VALUE;
        int myMaxIntValue = Integer.MAX_VALUE;
        System.out.println("myValue: " + myValue); // 10000
        System.out.println("Integer value ranges from (" + myMinIntValue + " to " + myMaxIntValue + ")"); // -2147483648 to 2147483647
        System.out.println("Busted MAX value of an int is: " + (myMaxIntValue + 1)); // Overflow
        System.out.println("Busted MIN value of an int is: " + (myMinIntValue - 1)); // Underflow

        int myMaxIntTest = 2_147_483_647; // Underscores are allowed in Java 7 and above
        System.out.println("myMaxIntTest: " + myMaxIntTest); // 2147483647
        System.out.println("The integer has a width of " + Integer.SIZE + " bits"); // 32 bits

        System.out.println();

        /*
         * Here int is a data-type and Integer is a wrapper class for int data-type.
         * 
         * So what is a class?
         * A class is a building block for object-oriented programming, and allows use to build custom data-types
         * Java uses the concept of wrapper classes to wrap primitive data-types into classes.
         * A wrapper class provides simple operations, as well as some basic information about the primitive data-type
         */

        // byte
        byte myMinByteValue = Byte.MIN_VALUE;
        byte myMaxByteValue = Byte.MAX_VALUE;
        System.out.println("Byte value ranges from (" + myMinByteValue + " to " + myMaxByteValue + ")"); // -128 to 127
        System.out.println("The byte has a width of " + Byte.SIZE + " bits"); // 8 bits

        System.out.println();

        // short
        short myMinShortValue = Short.MIN_VALUE;
        short myMaxShortValue = Short.MAX_VALUE;
        System.out.println("Short value ranges from (" + myMinShortValue + " to " + myMaxShortValue + ")"); // -32768 to 32767
        System.out.println("The short has a width of " + Short.SIZE + " bits"); // 16 bits

        System.out.println();

        // long
        long myLongValue = 100L; // L is used to tell Java that this is a long value
        System.out.println("myLongValue: " + myLongValue); // 100
        long myMinLongValue = Long.MIN_VALUE;
        long myMaxLongValue = Long.MAX_VALUE;
        System.out.println("Long value ranges from (" + myMinLongValue + " to " + myMaxLongValue + ")"); // -9223372036854775808 to 9223372036854775807
        System.out.println("The long has a width of " + Long.SIZE + " bits"); // 64 bits
        // Casting of int, byte, long
        int myTotal = (myMinIntValue / 2);
        System.out.println("myTotal: " + myTotal); // -1073741824

        byte myNewByteValue = (byte) (myMinByteValue / 2);
        System.out.println("myNewByteValue: " + myNewByteValue); // -64

        short myNewShortValue = (short) (myMinShortValue / 2);
        System.out.println("myNewShortValue: " + myNewShortValue); // -16384

        System.out.println();

        // Challenge
        byte myByte = 10;
        short myShort = 20;
        int myInt = 50;
        long myLong = 50000L + 10L * (myByte + myShort + myInt);
        System.out.println("myLong: " + myLong); // 50800

        System.out.println();

        // float
        float myMinFloatValue = Float.MIN_VALUE, myMaxFloatValue = Float.MAX_VALUE;
        System.out.println("Float value ranges from (" + myMinFloatValue + " to " + myMaxFloatValue + ")"); // 1.4E-45 to 3.4028235E38
        System.out.println("The float has a width of " + Float.SIZE + " bits"); // 32 bits

        System.out.println();

        // double
        double myMinDoubleValue = Double.MIN_VALUE, myMaxDoubleValue = Double.MAX_VALUE;
        System.out.println("Double value ranges from (" + myMinDoubleValue + " to " + myMaxDoubleValue + ")"); // 4.9E-324 to 1.7976931348623157E308
        System.out.println("The double has a width of " + Double.SIZE + " bits"); // 64 bits
        float myFloatValue = 5.25f; // f is used to tell Java that this is a float value
        double myDoubleValue = 5.25;
        System.out.println("myFloatValue: " + myFloatValue); // 5.25
        System.out.println("myDoubleValue: " + myDoubleValue); // 5.25
        // Casting of float and double
        int myIntValue;
        myIntValue = 5 / 3;
        System.out.println("myIntValue: " + myIntValue); // 1
        myFloatValue = 5f / 3f;
        System.out.println("myFloatValue: " + myFloatValue); // 1.6666666
        myDoubleValue = 5d / 3d;
        System.out.println("myDoubleValue: " + myDoubleValue); // 1.6666666666666667

        System.out.println();

        // Challenge
        double pounds = 200d;
        double kilograms = pounds * 0.45359237d;
        System.out.println(pounds + " pounds = " + kilograms + " kilograms"); // 200.0 pounds = 90.718474 kilograms

        System.out.println();

        // char
        char myChar = 'A';
        System.out.println("myChar: " + myChar); // A
        System.out.println("The char has a width of " + Character.SIZE + " bits"); // 16 bits
        char myUnicodeChar = '\u0041';
        System.out.println("myUnicodeChar: " + myUnicodeChar); // A
        char myDecimalChar = 65;
        System.out.println("myDecimalChar: " + myDecimalChar); // A

        System.out.println();

        // Challenge
        char mySimpleChar = '?';
        char myUnicodeCharacter = '\u003F';
        char myDecimalCharacter = 63;
        System.out.println("mySimpleChar: " + mySimpleChar); // ?
        System.out.println("myUnicodeCharacter: " + myUnicodeCharacter); // ?
        System.out.println("myDecimalCharacter: " + myDecimalCharacter); // ?

        System.out.println();

        // boolean
        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;
        System.out.println("myTrueBooleanValue: " + myTrueBooleanValue); // true
        System.out.println("myFalseBooleanValue: " + myFalseBooleanValue); // false

    }
}
