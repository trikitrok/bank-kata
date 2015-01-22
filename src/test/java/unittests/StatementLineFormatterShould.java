package unittests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.Test;

import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.Transaction;

public class StatementLineFormatterShould {

    @Test
    public void print_a_single_formatted_transaction_on_the_console() {
        final Date A_DATE = new Date(114, 3, 10, 0, 0);
        final StatementLineFormatter statementLineFormatter = new StatementLineFormatter();

        assertThat(
                statementLineFormatter.format(new StatementLine(new Transaction(500, A_DATE), 1500)),
                is("10/04/2014 | 500.00 | 1500.00"));
    }

}
