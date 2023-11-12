package com.rishab;

import java.util.Scanner;
import java.util.Arrays;

public class VarArgs {
    public static void main(String[] args) {

        printText("Hello", "World", "Java");
        printText("Hello", "World");
        printText();

        String[] programmingLanguages = {"Java", "Python", "C++", "C", "JavaScript", "TypeScript", "Go", "Rust"};
        System.out.println(String.join(", ", programmingLanguages));

        System.out.println();

        int[] myIntArray = readIntegers(5);
        System.out.println("The array is: " + Arrays.toString(myIntArray));
        System.out.println("The minimum element in the array is: " + findMin(myIntArray));

        System.out.println();

        reverse(myIntArray);
        System.out.println("The array is: " + Arrays.toString(myIntArray));
        System.out.println("The reversed array is: " + Arrays.toString(myIntArray));
        int[] reversedArray = reverseCopy(myIntArray);
        System.out.println("The array is: " + Arrays.toString(myIntArray));
        System.out.println("The reversed array is: " + Arrays.toString(reversedArray));

    }

    private static void printText(String... textList) {
        if (textList.length == 0) {
            System.out.println("No text to print");
            return;
        }
        for (String text : textList) {
            System.out.print(text);
        }
    }

    // Challenge
    private static int[] readIntegers(int size) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.print("Enter number #" + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
        scanner.close();
        return array;
    }

    private static int findMin(int[] array) {
        int min = Integer.MAX_VALUE;

        for (int element : array) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }

    private static void reverse(int[] array) {
        int temp;
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
    }

    private static int[] reverseCopy(int[] array) {
        int[] reversedArray = Arrays.copyOf(array, array.length);
        int length = array.length - 1;
        for (int element: array) {
            reversedArray[length--] = element;
        }
        return reversedArray;
    }
}
