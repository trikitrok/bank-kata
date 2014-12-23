package unittests;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import bank.InMemoryTransactions;
import bank.SystemDate;
import bank.Transaction;
import bank.Transactions;

public class InMemoryTransactionsShould {

    private static final String A_DATE = "10/04/2014";
    private int amount;
    private SystemDate systemDate;
    private Transactions transactions;

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

        assertThat(transactions.contains(new Transaction(amount, A_DATE)), is(true));
    }

    @Test
    public void not_find_not_stored_transaction() {
        transactions.register(amount);

        assertThat(transactions.contains(new Transaction(-amount, A_DATE)), is(false));
    }

}
