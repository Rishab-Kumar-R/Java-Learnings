package com.rishab.immutableChallenges.bank;

import com.rishab.immutableChallenges.dto.Transaction;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankAccount {
    // enums are immutable
    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;
    private double balance;
    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();

    BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Long, String> getTransactions() {
        Map<Long, String> transactionMap = new LinkedHashMap<>();
        for (var transaction : transactions.entrySet()) {
            transactionMap.put(transaction.getKey(), transaction.getValue().toString());
        }
        return transactionMap;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }

    void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        balance += amount;
        transactions.put(transactionId,
            new Transaction(routingNumber, transactionId, Integer.parseInt(customerId), amount));
    }
}
