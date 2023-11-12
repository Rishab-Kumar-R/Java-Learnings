package com.rishab;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] myIntArray = new int[10];
        // myIntArray[0] = 14.50; // This is not allowed
        // myIntArray[1] = "Hello"; // This is not allowed
        myIntArray[5] = 50;
        System.out.println(myIntArray[5]);

        double[] myDoubleArray = new double[10];
        myDoubleArray[2] = Math.PI;
        System.out.println(myDoubleArray[2]);

        int[] myIntArray2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int element : myIntArray2) {
            System.out.print(element + ", ");
        }
        System.out.println("\nThe length of myIntArray2 is " + myIntArray2.length);
        System.out.println("The last element of myIntArray2 is " + myIntArray2[myIntArray2.length - 1]);

        int[] newArray;
        // newArray = new int[]{5, 4, 3, 2, 1};
        newArray = new int[5];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = newArray.length - i;
        }
        for (int element : newArray) {
            System.out.print(element + ", ");
        }

        System.out.println("\n" + newArray); // Prints the address of the array
        System.out.println(Arrays.toString(newArray)); // Prints the array

        Object objectVariable = newArray;
        if (objectVariable instanceof int[]) {
            System.out.println("objectVariable is an instance of int[]");
        }

        // Creating an array of objects
        Object[] objectArray = new Object[3];
        objectArray[0] = "Hello";
        objectArray[1] = new StringBuilder("World");
        objectArray[2] = newArray;

        System.out.println();

        // let's get hands on with the java.util.Arrays class
        int[] firstArray = getRandomArray(10);
        System.out.println(Arrays.toString(firstArray));
        Arrays.sort(firstArray);
        System.out.println(Arrays.toString(firstArray));

        int[] secondArray = new int[10];
        System.out.println(Arrays.toString(secondArray));
        Arrays.fill(secondArray, 5);
        System.out.println(Arrays.toString(secondArray));

        int[] thirdArray = getRandomArray(10);
        System.out.println(Arrays.toString(thirdArray));

        int[] forthArray = Arrays.copyOf(thirdArray, thirdArray.length);
        System.out.println(Arrays.toString(forthArray));
        Arrays.sort(forthArray);
        System.out.println(Arrays.toString(thirdArray));
        System.out.println(Arrays.toString(forthArray));

        int[] smallerArray = Arrays.copyOf(thirdArray, 5);
        System.out.println(Arrays.toString(smallerArray));

        int[] largerArray = Arrays.copyOf(thirdArray, 15);
        System.out.println(Arrays.toString(largerArray));

        String[] stringArray = {"California", "Texas", "New York", "Florida", "Illinois", "Pennsylvania", "Ohio", "Georgia", "North Carolina", "Michigan"};
        Arrays.sort(stringArray);
        System.out.println(Arrays.toString(stringArray));
        if (Arrays.binarySearch(stringArray, "Texas") >= 0) {
            System.out.println("Texas is in the list");
        }

        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {5, 2, 3, 1, 4};
        if(Arrays.equals(array1, array2)) {
            System.out.println("array1 and array2 are equal");
        } else {
            System.out.println("array1 and array2 are not equal");
        }

        System.out.println();

        int[] unsortedArray = getRandomArray(10);
        System.out.println(Arrays.toString(unsortedArray));

        int[] sortedArray = sortArrayInDescendingOrder(unsortedArray);
        System.out.println("The sorted array is " + Arrays.toString(sortedArray));
    }

    private static int[] getRandomArray(int length) {
        Random random = new Random();
        int[] newInt = new int[length];
        for (int i = 0; i < length; i++) {
            newInt[i] = random.nextInt(100);
        }
        return newInt;
    }

    // Challenge
    private static int[] sortArrayInDescendingOrder(int[] array) {
        System.out.println("The original array is " + Arrays.toString(array));
        int[] sortedArray = Arrays.copyOf(array, array.length);

        boolean flag = true;
        int temp;

        while (flag) {
            flag = false;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                if (i == sortedArray.length - 1) {
                    break;
                }
                if (sortedArray[i] < sortedArray[i + 1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                    flag = true;
                }
            }
        }
        return sortedArray;
    }
}
