/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bank_statements_analyzer;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static BankTransaction maximumTransactionInPeriod(LocalDate date1, LocalDate date2,
            List<BankTransaction> transactions) {

        BankTransaction biggestTransaction = new BankTransaction(null, 0, null);
        for (BankTransaction transaction : transactions) {
            LocalDate transDate = transaction.getDate();
            if (transDate.isAfter(date1) && transDate.isBefore(date2)) {
                if (transaction.getAmount() > biggestTransaction.getAmount()) {
                    biggestTransaction = transaction;
                }
            }
        }

        return biggestTransaction;
    }

    public static BankTransaction minimuTransactionInPeriod(LocalDate date1, LocalDate date2,
            List<BankTransaction> transactions) {

        BankTransaction lowestTransaction = new BankTransaction(null, 0, null);
        for (BankTransaction transaction : transactions) {
            LocalDate transDate = transaction.getDate();
            if (transDate.isAfter(date1) && transDate.isBefore(date2)) {
                if (transaction.getAmount() < lowestTransaction.getAmount()
                        || lowestTransaction.getAmount() == 0) {
                    lowestTransaction = transaction;
                }
            }
        }

        return lowestTransaction;
    }

    private List<Group> agroupTransactions() {
        List<Group> groups = new ArrayList<>();
        for (BankTransaction transaction : transactions) {
            if (groups.isEmpty()) {
                groups.add(new Group(transaction.getDate().getMonth(), transaction.getDescription()));
            } else {
                boolean monthExist = false;
                for (Group group : groups) {
                    if (group.getMonth().equals(transaction.getDate().getMonth())) {
                        group.addDescription(transaction.getDescription());
                        monthExist = true;
                    }
                }
                if (!monthExist) {
                    groups.add(new Group(transaction.getDate().getMonth(), transaction.getDescription()));
                }
            }
        }
        return groups;
    }

    public void drawTransactionsHistogram() {
        for (Group group : agroupTransactions()) {
            Map<String, Integer> map = new HashMap<>();
            for (String key : group.getDescriptions()) {
                if (!map.containsKey(key)) {
                    map.put(key, 1);
                } else {
                    int currentValue = map.get(key);
                    map.replace(key, ++currentValue);
                }
            }
            System.out.println("MONTH: " + group.getMonth());
            System.out.println("\tDescriptions: " + map);
        }
    }

    private class Group {

        private Month month;
        private List<String> descriptions;

        public Group(Month month, String description) {
            this.descriptions = new ArrayList<>();
            this.month = month;
            this.descriptions.add(description);
        }

        public Month getMonth() {
            return month;
        }

        public void setMonth(Month month) {
            this.month = month;
        }

        public List<String> getDescriptions() {
            return descriptions;
        }

        public void addDescription(String description) {
            this.descriptions.add(description);
        }

        @Override
        public String toString() {
            return "Month: " + month + "\nDescriptions: \n" + descriptions;
        }
    }
}
