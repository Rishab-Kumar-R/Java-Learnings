package com.rishab;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Addition addition = new Addition();
        System.out.println("The sum of 2 and 3 is: " + addition.add(2, 3));
        
        System.out.println(NumberUtils.toDouble("1234.5"));

    }
}
