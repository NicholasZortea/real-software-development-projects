package bank_statements_test;

import com.mycompany.bank_statements_analyzer.BankStatementsCSVParser;
import com.mycompany.bank_statements_analyzer.BankTransaction;
import java.time.LocalDate;
import java.time.Month;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author zorte
 */
public class BankStatementCSVParserTest {

    private final BankStatementsCSVParser parser
            = new BankStatementsCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        //context
        final String lineToParse = "30-01-2017,-50,Tesco";

        //action
        BankTransaction transaction = parser.parseFrom(lineToParse);
        final BankTransaction expected = new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");

        //assertions
        Assert.assertEquals(expected.getDate(), transaction.getDate());
        Assert.assertEquals(expected.getAmount(), transaction.getAmount());
        Assert.assertEquals(expected.getDescription(), transaction.getDescription());
    }
}
