package unittests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static unittests.helpers.StatementBuilder.aStatement;

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
import bank.Transactions;

public class InMemoryTransactionsShould {

    private static final Date A_DATE = new Date(2014, 11, 23, 20, 4);

    @Test
    public void store_a_transaction() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(A_DATE);
        InMemoryTransactions transactions = new InMemoryTransactions(systemDate);

        transactions.register(1);
        assertThat(transactions.hasAlreadyRegistered(new Transaction(1, A_DATE)), is(true));
    }

    @Test
    public void not_find_not_stored_transaction() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(A_DATE);
        InMemoryTransactions transactions = new InMemoryTransactions(systemDate);

        transactions.register(3);

        assertThat(transactions.hasAlreadyRegistered(new Transaction(-3, A_DATE)), is(false));
    }

    @Test
    public void generate_a_statement() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(new Date(114, 2, 10)).thenReturn(new Date(114, 2, 11));
        Transactions transactions = new InMemoryTransactions(systemDate);
        transactions = new InMemoryTransactions(systemDate);
        transactions.register(100);
        transactions.register(-50);

        Statement statement = transactions.generateStatement();

        assertThat(
                statement,
                equalTo(aStatement().withLines(
                        new StatementLine(new Transaction(-50, new Date(114, 2, 11)), 50),
                        new StatementLine(new Transaction(100, new Date(114, 2, 10)), 100))));
    }
}
