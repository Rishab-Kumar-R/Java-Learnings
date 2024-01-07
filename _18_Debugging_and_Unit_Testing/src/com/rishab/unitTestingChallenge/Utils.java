package com.rishab.unitTestingChallenge;

public class Utils {

    /**
     * Returns a char array containing every nth character from sourceArray
     *
     * @param sourceArray char[] to be processed
     * @param n           int representing the nth character to be returned
     * @return char[] containing every nth character from sourceArray when sourceArray.length < n
     */
    public char[] everyNthChar(char[] sourceArray, int n) {
        if (sourceArray == null || sourceArray.length < n) {
            return sourceArray;
        }

        int returnedLength = sourceArray.length / n;
        char[] result = new char[returnedLength];
        int index = 0;

        for (int i = n - 1; i < sourceArray.length; i += n) {
            result[index++] = sourceArray[i];
        }
        return result;
    }

    /**
     * Removes pairs of the same character that are next to each other, by removing one occurrence of the character
     *
     * @param src String to be processed
     * @return String with pairs of the same character that are next to each other removed
     */
    public String removePairs(String src) {
        if (src == null || src.length() < 2) {
            return src;
        }

        StringBuilder sb = new StringBuilder();
        char[] string = src.toCharArray();

        for (int i = 0; i < string.length - 1; i++) {
            System.out.println(string[i]);
            if (string[i] != string[i + 1]) {
                sb.append(string[i]);
            }
        }

        System.out.println(string[string.length - 1]);
//        adding the final character which is not checked in the for loop
        sb.append(string[string.length - 1]);
        return sb.toString();
    }

    /**
     * Performs an arithmetic operation on two integers
     *
     * @param a int representing the first integer
     * @param b int representing the second integer
     * @return int representing the result of the arithmetic operation
     */
    public int converter(int a, int b) {
        return (a / b) + (a * 30) - 2;
    }

    /**
     * Returns null if the length of the string is odd, otherwise returns the string
     *
     * @param src String to be processed
     * @return null if the length of the string is odd, otherwise returns the string
     */
    public String nullIfOddLength(String src) {
        if (src.length() % 2 == 0) {
            return src;
        }
        return null;
    }
}
