/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bank_statements_analyzer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zorte
 */
public class MainApplication {

    public static void main(String[] args) {
        BankStatementsCSVParser csvParser = new BankStatementsCSVParser();
        Bank_Statements_Analyzer analyzer = new Bank_Statements_Analyzer();
        try {
            analyzer.analyze("statements.csv", csvParser);
        } catch (IOException ex) {
            Logger.getLogger(MainApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
