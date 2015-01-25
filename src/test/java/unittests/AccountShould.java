package unittests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import bank.Account;
import bank.StatementPrinter;
import bank.Transactions;

public class AccountShould {
    StatementPrinter statementPrinter;
    private Transactions transactions;
    private Account account;

    @Before
    public void setUp() {
        statementPrinter = mock(StatementPrinter.class);
        transactions = mock(Transactions.class);
        account = new Account(statementPrinter, transactions);
    }

    @Test
    public void register_transaction_with_a_positive_amount_for_a_deposit() {
        account.deposit(100);

        verify(transactions).register(100);
    }

    @Test
    public void register_transaction_with_a_negative_amount_for_a_withdraw() {
        account.withdraw(100);

        verify(transactions).register(-100);
    }
}
