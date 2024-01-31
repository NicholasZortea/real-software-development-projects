package com.mycompany.bank_statements_analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 *
 * @author zorte
 */
public class Bank_Statements_Analyzer {

    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final String fileName, BankStatementsParser parser) throws IOException {
        Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        List<BankTransaction> listOfTransactions = parser.parseLinesFrom(lines);
        BankStatementsProcessor processor = new BankStatementsProcessor(listOfTransactions);

        System.out.println("total amount: "
                + BankStatementsProcessor.calculateTotalAmount(listOfTransactions));
        System.out.println("Transactions in February: "
                + BankStatementsProcessor.selectInMonth(listOfTransactions,
                        Month.FEBRUARY));

        System.out.println("Total in Tesco: " + processor.calculateTotalForCategory("Tesco"));
        System.out.println("Total in January: " + processor.calculateTotalInMonth(Month.JANUARY));

        System.out.println("Biggest Transaction:");
        LocalDate date2 = LocalDate.parse("2017-02-05");
        LocalDate date1 = LocalDate.parse("2017-01-30");
        System.out.println(BankStatementsProcessor.maximumTransactionInPeriod(date1, date2, listOfTransactions));
        
        System.out.println("Lowest Transaction: ");
        System.out.println(BankStatementsProcessor.minimuTransactionInPeriod(date1, date2, listOfTransactions));
        processor.drawTransactionsHistogram();
    }
}
