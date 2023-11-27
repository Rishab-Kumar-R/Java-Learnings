package com.rishab.immutableChallenges;

import com.rishab.immutableChallenges.bank.Bank;
import com.rishab.immutableChallenges.bank.BankAccount;
import com.rishab.immutableChallenges.bank.BankCustomer;
import com.rishab.immutableChallenges.dto.Transaction;

import java.util.List;

// We can't create a subclass of BankAccount because it's modifier is neither public nor protected
// class MyAccount extends BankAccount {
//     MyAccount(AccountType accountType, double balance) {
//         super(accountType, balance);
//     }
// }

public class Main {
    public static void main(String[] args) {

        // BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING, 500);
        // System.out.println(account);

        // BankCustomer customer = new BankCustomer("Alex", 500, 10000);
        // System.out.println(customer);

        Bank bank = new Bank(5378172);
        bank.addCustomer("Alex", 500, 10000);

        BankCustomer customer = bank.getCustomer("000000010000000");
        System.out.println(customer);

        if (bank.doTransaction(customer.getCustomerId(), BankAccount.AccountType.CHECKING, 35)) {
            System.out.println("Transaction successful");
            System.out.println(customer);
        } else {
            System.out.println("Transaction failed");
        }

        if (bank.doTransaction(customer.getCustomerId(), BankAccount.AccountType.CHECKING, -535)) {
            System.out.println("Transaction successful");
            System.out.println(customer);
        } else {
            System.out.println("Transaction failed");
        }

        BankAccount checking = customer.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((id, transaction) -> System.out.println(id + " -> " + transaction));

        // We can't modify the transaction map because it's modifier is neither public nor protected
        // transactions.put(3L,
        //     new Transaction(1234567, 1, Integer.parseInt(customer.getCustomerId()), 500));

        // System.out.println("--------------------------------------------------");
        // for (var tx : transactions.values()) {
        //     tx.setCustomerId(2);
        //     tx.setAmount(10000);
        // }
        // transactions.forEach((id, transaction) -> System.out.println(id + " -> " + transaction));

        customer.getAccount(BankAccount.AccountType.CHECKING).getTransactions().clear();

        System.out.println("--------------------------------------------------");
        customer.getAccount(BankAccount.AccountType.CHECKING).getTransactions()
            .forEach((id, transaction) -> System.out.println(id + " -> " + transaction));

    }
}
