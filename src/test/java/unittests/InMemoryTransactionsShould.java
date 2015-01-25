package unittests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static unittests.helpers.DateBuilder.date;
import static unittests.helpers.StatementBuilder.aStatement;

import java.util.Date;

import org.junit.Test;

import bank.InMemoryTransactions;
import bank.Statement;
import bank.StatementLine;
import bank.SystemDate;
import bank.Transaction;
import bank.Transactions;

public class InMemoryTransactionsShould {

    private static final Date ANY_DATE = date(23, 12, 2014);

    @Test
    public void store_a_transaction() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(ANY_DATE);
        InMemoryTransactions transactions = new InMemoryTransactions(systemDate);

        transactions.register(1);
        assertThat(transactions.hasAlreadyRegistered(new Transaction(1, ANY_DATE)), is(true));
    }

    @Test
    public void not_find_not_stored_transaction() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(ANY_DATE);
        InMemoryTransactions transactions = new InMemoryTransactions(systemDate);

        transactions.register(3);

        assertThat(transactions.hasAlreadyRegistered(new Transaction(-3, ANY_DATE)), is(false));
    }

    @Test
    public void generate_a_statement() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(date(10, 3, 2014)).thenReturn(date(11, 3, 2014));
        Transactions transactions = new InMemoryTransactions(systemDate);
        transactions = new InMemoryTransactions(systemDate);
        transactions.register(100);
        transactions.register(-50);

        Statement statement = transactions.generateStatement();

        assertThat(statement,
                   equalTo(aStatement().withLines(new StatementLine(
                                                          new Transaction(-50, date(11, 3, 2014)),
                                                          50),
                                                  new StatementLine(
                                                          new Transaction(100, date(10, 3, 2014)),
                                                          100))));
    }
}
