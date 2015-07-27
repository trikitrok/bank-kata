package unittests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static unittests.helpers.DateFactory.date;
import static unittests.helpers.StatementFactory.statementLine;

import org.junit.Test;

import bank.accounts.statements.printing.StatementLineFormatter;

public class StatementLineFormatterShould {

    @Test
    public void format_a_statement_line() {
        StatementLineFormatter statementLineFormatter = new StatementLineFormatter();

        assertThat(statementLineFormatter.format(statementLine(500, date(10, 4, 2014), 1500)),
                is("10/04/2014 | 500.00 | 1500.00"));
    }
}
