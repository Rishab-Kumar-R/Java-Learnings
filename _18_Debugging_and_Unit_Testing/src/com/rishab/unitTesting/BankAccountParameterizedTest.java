package com.rishab.unitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountParameterizedTest {
    private BankAccount account;

    @BeforeEach
    public void setup() {
        account = new BankAccount("Karan", "Singh", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @ParameterizedTest
    @MethodSource("testConditions")
    void deposit(double amount, boolean branch, double expected) {
        account.deposit(amount, branch);
        assertEquals(expected, account.getBalance(), 0.01);
    }

    public static Stream<Object[]> testConditions() {
        return Stream.of(new Object[][]{
            {100.00, true, 1100.00},
            {200.00, true, 1200.00},
            {325.14, true, 1325.14},
            {489.33, true, 1489.33},
            {1000.00, true, 2000.00}
        });
    }
}
