package com.rishab;

import java.util.Arrays;

public class MultiDimensionalArrays {
    public static void main(String[] args) {

        int[][] array = new int[4][4];
        System.out.println("array = " + Arrays.toString(array));
        System.out.println("array.length = " + array.length);

        for (int[] outer : array) {
            System.out.println("outer = " + Arrays.toString(outer));
        }

        for (int i = 0; i < array.length; i++) {
            var innerArray = array[i];
            for (int j = 0; j < innerArray.length; j++) {
                // System.out.print("array[" + i + "][" + j + "] = " + array[i][j] + ", ");
                array[i][j] = (i + 1) * (j + 1);
            }
            // System.out.println();
        }

        // for (var outer: array) {
        //     for (var element: outer) {
        //         System.out.print("element = " + element + ", ");
        //     }
        //     System.out.println();
        // }

        System.out.println("array = " + Arrays.deepToString(array));

        array[1] = new int[]{10, 20, 30};
        System.out.println("array = " + Arrays.deepToString(array));

        System.out.println();

        Object[] objectArray = new Object[3];
        System.out.println("objectArray = " + Arrays.toString(objectArray));

        objectArray[0] = new String[]{"Hello", "World"};
        System.out.println("objectArray = " + Arrays.deepToString(objectArray));

        objectArray[1] = new String[][]{
            {"One", "Two"},
            {"Three", "Four", "Five", "Six"},
            {"Seven", "Eight", "Nine"}
        };
        System.out.println("objectArray = " + Arrays.deepToString(objectArray));

        objectArray[2] = new int[2][2][2];
        for (int i = 0; i < ((int[][][]) objectArray[2]).length; i++) {
            for (int j = 0; j < ((int[][][]) objectArray[2])[i].length; j++) {
                for (int k = 0; k < ((int[][][]) objectArray[2])[i][j].length; k++) {
                    ((int[][][]) objectArray[2])[i][j][k] = (i + 1) * (j + 1) * (k + 1);
                }
            }
        }
        System.out.println("objectArray = " + Arrays.deepToString(objectArray));
        for (Object element : objectArray) {
            System.out.println("element type = " + element.getClass().getSimpleName());
            System.out.println("element toString() = " + element);
            System.out.println("element = " + Arrays.deepToString((Object[]) element));
        }

    }
}
