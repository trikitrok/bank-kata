package unittests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static unittests.helpers.DateBuilder.date;
import static unittests.helpers.StatementBuilder.statement;
import static unittests.helpers.StatementLineBuilder.statementLine;

import java.util.Date;

import org.junit.Test;

import bank.accounts.statements.Statement;
import bank.accounts.transactions.InMemoryTransactions;
import bank.accounts.transactions.Transaction;
import bank.accounts.transactions.Transactions;
import bank.system.adapters.SystemDate;

public class InMemoryTransactionsShould {

    private static final Date ANY_DATE = date(23, 12, 2014);

    @Test
    public void store_a_transaction() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(ANY_DATE);
        InMemoryTransactions transactions = new InMemoryTransactions(systemDate);

        transactions.recordTransactionOf(1);

        assertThat(transactions.hasAlreadyRegistered(new Transaction(1, ANY_DATE)), is(true));
    }

    @Test
    public void not_find_not_stored_transaction() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(ANY_DATE);
        InMemoryTransactions transactions = new InMemoryTransactions(systemDate);

        transactions.recordTransactionOf(3);

        assertThat(transactions.hasAlreadyRegistered(new Transaction(-3, ANY_DATE)), is(false));
    }

    @Test
    public void generate_a_statement_with_lines_in_reverse_transaction_recording_order() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(date(10, 3, 2014)).thenReturn(date(11, 3, 2014));
        Transactions transactions = new InMemoryTransactions(systemDate);
        transactions = new InMemoryTransactions(systemDate);
        transactions.recordTransactionOf(100);
        transactions.recordTransactionOf(-50);

        Statement statement = transactions.generateStatement();

        assertThat(statement,
                   equalTo(statement().withLines(statementLine(-50, date(11, 3, 2014), 50),
                                                 statementLine(100, date(10, 3, 2014), 100))));
    }
}
