package unittests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bank.InMemoryTransactions;
import bank.Statement;
import bank.StatementLine;
import bank.SystemDate;
import bank.Transaction;

public class InMemoryTransactionsShould {

    private static final Date A_DATE = new Date(2014, 11, 23, 20, 4);
    private int amount;
    private SystemDate systemDate;
    private InMemoryTransactions transactions;

    @Before
    public void setUp() {
        amount = 1;
        systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(A_DATE);
        transactions = new InMemoryTransactions(systemDate);
    }

    @Test
    public void store_a_transaction() {
        transactions.register(amount);
        assertThat(transactions.hasAlreadyRegistered(new Transaction(amount, A_DATE)), is(true));
    }

    @Test
    public void not_find_not_stored_transaction() {
        transactions.register(amount);

        assertThat(transactions.hasAlreadyRegistered(new Transaction(-amount, A_DATE)), is(false));
    }

    @Test
    public void generate_a_statement() {
        systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(new Date(114, 2, 10)).thenReturn(new Date(114, 2, 11));
        transactions = new InMemoryTransactions(systemDate);
        transactions.register(100);
        transactions.register(-50);
        List<StatementLine> statementLines = new ArrayList<StatementLine>();
        statementLines.add(new StatementLine(new Transaction(-50, new Date(114, 2, 11)), 50));
        statementLines.add(new StatementLine(new Transaction(100, new Date(114, 2, 10)), 100));
        Statement expectedStatement = new Statement(statementLines);

        Statement statement = transactions.generateStatement();

        assertThat(statement, equalTo(expectedStatement));
    }
}
