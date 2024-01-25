/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bank_statements_analyzer;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zorte
 */
public class BankStatementsProcessor {

    private List<BankTransaction> transactions;

    public BankStatementsProcessor(List<BankTransaction> list) {
        this.transactions = list;
    }

    public static double calculateTotalAmount(List<BankTransaction> transactions) {
        double total = 0;
        for (BankTransaction transaction : transactions) {
            total += transaction.getAmount();
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(List<BankTransaction> transactions, Month month) {
        List<BankTransaction> transactionsInMonth = new ArrayList<BankTransaction>();
        for (BankTransaction transaction : transactions) {
            if (transaction.getDate().getMonth() == month) {
                transactionsInMonth.add(transaction);
            }
        }
        return transactionsInMonth;
    }

    public double calculateTotalInMonth(Month month) {
        double total = 0;
        List<BankTransaction> transactionsInMonth = selectInMonth(transactions, month);
        total = calculateTotalAmount(transactionsInMonth);
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (BankTransaction transaction : transactions) {
            if (transaction.getDescription().toUpperCase().
                    equals(category.toUpperCase())) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
}
