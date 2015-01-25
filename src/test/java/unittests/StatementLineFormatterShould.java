package unittests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static unittests.helpers.DateBuilder.date;

import org.junit.Test;

import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.Transaction;

public class StatementLineFormatterShould {

    @Test
    public void format_a_statement_line() {
        StatementLineFormatter statementLineFormatter = new StatementLineFormatter();

        assertThat(statementLineFormatter.format(new StatementLine(
                           new Transaction(500, date(10, 4, 2014)), 1500)),
                   is("10/04/2014 | 500.00 | 1500.00"));
    }

}
