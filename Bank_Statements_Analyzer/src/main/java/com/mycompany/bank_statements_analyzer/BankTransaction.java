/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bank_statements_analyzer;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author zorte
 */
public class BankTransaction {

    private LocalDate date;
    private double amount;
    private String description;

    public BankTransaction(LocalDate date, double amount, String description) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", amount: " + amount + ", description: "
                + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankTransaction that = (BankTransaction) o;
        return Double.compare(that.amount, amount) == 0
                && date.equals(that.date)
                && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description);
    }

    public static double calculateTotalAmount(List<BankTransaction> transactions) {
        double total = 0;
        for (BankTransaction transaction : transactions) {
            total += transaction.amount;
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(List<BankTransaction> transactions, Month month) {
        List<BankTransaction> transactionsInMonth = new ArrayList<BankTransaction>();
        for (BankTransaction transaction : transactions) {
            if (transaction.date.getMonth() == month) {
                transactionsInMonth.add(transaction);
            }
        }
        return transactionsInMonth;
    }

}
