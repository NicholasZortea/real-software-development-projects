package com.mycompany.bank_statements_analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 *
 * @author zorte
 */
public class Bank_Statements_Analyzer {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(RESOURCES + "statements.csv");
        List<String> lines = Files.readAllLines(path);

        BankStatmentsCSVParser parser = new BankStatmentsCSVParser();
        List<BankTransaction> listOfTransactions = parser.parseLinesFromCSV(lines);
        listOfTransactions.stream().forEach((t) -> {
            System.out.println(t);
        });
        System.out.println("total amount: " + BankTransaction.calculateTotalAmount(listOfTransactions));
        System.out.println("Transactions in February: " + BankTransaction.selectInMonth(listOfTransactions, Month.FEBRUARY));
    }
}
