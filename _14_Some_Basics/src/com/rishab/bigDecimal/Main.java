package com.rishab.bigDecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        double policyAmount = 100_000_000;
        int beneficiaryCount = 3;
        float percentageFloat = 1.0f / beneficiaryCount;
        double percentageDouble = 1.0 / beneficiaryCount;

        System.out.printf("Payout = %,.2f%n", policyAmount * percentageFloat);
        System.out.printf("Payout = %,.2f%n", policyAmount * percentageDouble);

        double totalUsingFloat = policyAmount - ((policyAmount * percentageFloat) * beneficiaryCount);
        System.out.printf("Total using float = %,.2f%n", totalUsingFloat);

        double totalUsingDouble = policyAmount - ((policyAmount * percentageDouble) * beneficiaryCount);
        System.out.printf("Total using double = %,.2f%n", totalUsingDouble);

        String[] tests = {"15.242", "2", "13483.000001", ".125"};
        BigDecimal[] bigDecimals = new BigDecimal[tests.length];
        Arrays.setAll(bigDecimals, i -> new BigDecimal(tests[i]));

        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for (BigDecimal bd : bigDecimals) {
            System.out.printf("%-14s %-15s %-8s %s%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            System.out.printf("%-14s %-15s %-8s %s%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }

        // double[] doubles = {15.242, 2, 13483.000001, .1234};
        // Arrays.setAll(bigDecimals, i -> BigDecimal.valueOf(doubles[i]));
        // System.out.println("—".repeat(50));
        // System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        // for (BigDecimal bd : bigDecimals) {
        //     System.out.printf("%-14s %-15s %-8s %s%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        // }

        // System.out.println("—".repeat(50));
        // BigDecimal bd1 = new BigDecimal("1.1111111111222223333334444"); // preferred
        // BigDecimal bd2 = BigDecimal.valueOf(1.1111111111222223333334444); // less preferred
        // System.out.printf("%-30s %-30s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        // System.out.printf("%-30s %-30s %-8s %s%n", bd1, bd1.unscaledValue(), bd1.scale(), bd1.precision());
        // System.out.printf("%-30s %-30s %-8s %s%n", bd2, bd2.unscaledValue(), bd2.scale(), bd2.precision());

        BigDecimal policyPayout = new BigDecimal("100e6");
        System.out.printf("%n%-14s %-15s %-8s %s%n",
            policyPayout, policyPayout.unscaledValue(), policyPayout.scale(), policyPayout.precision());

        BigDecimal percent = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaryCount), new MathContext(60, RoundingMode.UP));
        System.out.println("\n" + percent);

        BigDecimal checkAmount = policyPayout.multiply(percent);
        System.out.printf("%n%.2f", checkAmount);
        checkAmount = checkAmount.setScale(2, RoundingMode.HALF_UP);
        System.out.printf("%n%-14s %-15s %-8s %s%n",
            checkAmount, checkAmount.unscaledValue(), checkAmount.scale(), checkAmount.precision());

        BigDecimal total = checkAmount.multiply(BigDecimal.valueOf(beneficiaryCount));
        System.out.printf("%nCombined: %.2f%n", total);
        System.out.println("Remaining = " + policyPayout.subtract(total));
        System.out.printf("%n%-14s %-15s %-8s %s%n",
            total, total.unscaledValue(), total.scale(), total.precision());

    }
}
