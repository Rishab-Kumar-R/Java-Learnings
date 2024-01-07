package com.rishab.unitTesting;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount account;
    private static int count;

    @BeforeAll
    public static void beforeClass() {
        System.out.println("This executes before any test cases...count = " + count++);
    }

    @BeforeEach
    public void setup() {
        account = new BankAccount("Karan", "Singh", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @Test
    void deposit() {
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
    }

    @Test
    void withdraw_branch() {
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    @Test
    void withdraw_notBranch() {
//        double balance = account.withdraw(600.00, false);
//        assertEquals(400.00, balance, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(600.00, false);
            fail("Should have thrown an IllegalArgumentException");
        });
    }

    @Test
    void getBalance_deposit() {
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @Test
    void getBalance_withdraw() {
        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @Test
    public void isChecking_true() {
        assertTrue(account.isChecking(), "The account is NOT a checking account");
    }

//    @org.junit.jupiter.api.Test
//    public void dummyTest() {
//        assertEquals(20, 21);
//    }

    @AfterAll
    public static void afterClass() {
        System.out.println("This executes after all test cases...count = " + count++);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("count = " + count++);
    }
}
