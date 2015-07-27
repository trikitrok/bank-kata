package unittests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static unittests.helpers.DateFactory.date;
import static unittests.helpers.StatementBuilder.statement;
import static unittests.helpers.StatementFactory.statementLine;

import java.util.Date;

import bank.accounts.statements.StatementGenerator;
import org.junit.Before;
import org.junit.Test;

import bank.accounts.statements.Statement;
import bank.accounts.transactions.InMemoryTransactions;
import bank.accounts.transactions.Transaction;
import bank.accounts.transactions.Transactions;
import bank.system.SystemDate;

public class InMemoryTransactionsShould {

    private static final Date ANY_DATE = date(23, 12, 2014);
    private SystemDate systemDate;
    private InMemoryTransactions transactions;

    @Before
    public void setUp() throws Exception {
        systemDate = mock(SystemDate.class);
        transactions = new InMemoryTransactions(systemDate, new StatementGenerator());
    }

    @Test
    public void record_a_transaction() {
        when(systemDate.now()).thenReturn(ANY_DATE);

        transactions.recordTransactionOf(1);

        assertThat(transactions.hasAlreadyRecorded(new Transaction(1, ANY_DATE)), is(true));
    }

    @Test
    public void not_find_a_not_recorded_transaction() {
        when(systemDate.now()).thenReturn(ANY_DATE);
        Transaction notRecordedTransaction = new Transaction(-3, ANY_DATE);

        transactions.recordTransactionOf(3);

        assertThat(transactions.hasAlreadyRecorded(notRecordedTransaction), is(false));
    }

    @Test
    public void generate_a_statement_whose_lines_are_in_reverse_transaction_recording_order() {
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(date(10, 3, 2014)).thenReturn(date(11, 3, 2014));
        Transactions transactions = new InMemoryTransactions(systemDate, new StatementGenerator());
        transactions.recordTransactionOf(100);
        transactions.recordTransactionOf(-50);

        Statement statement = transactions.statement();

        assertThat(statement,
                equalTo(statement().withLines(
                        statementLine(-50, date(11, 3, 2014), 50),
                        statementLine(100, date(10, 3, 2014), 100))));
    }
}
