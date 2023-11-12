package com.rishab;

import java.util.Arrays;

public class ReferenceTypes {
    public static void main(String[] args) {

        int[] myIntArray = new int[5]; // myIntArray is a reference type
        int[] anotherArray = myIntArray;

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

        anotherArray[0] = 1;
        myIntArray[2] = 5;
        modifyArray(myIntArray);
        System.out.println("After change myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("After change anotherArray = " + Arrays.toString(anotherArray));

    }

    private static void modifyArray(int[] array) {
        array[1] = 2;
    }
}
