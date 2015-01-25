package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import bank.Account;
import bank.Statement;
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
    public void record_transaction_with_a_positive_amount_for_a_deposit() {
        account.deposit(100);

        verify(transactions).recordTransactionOf(100);
    }

    @Test
    public void record_transaction_with_a_negative_amount_for_a_withdraw() {
        account.withdraw(100);

        verify(transactions).recordTransactionOf(-100);
    }

    @Test
    public void print_a_statement() {
        account.printStatement();

        verify(transactions).generateStatement();
        verify(statementPrinter).print(any(Statement.class));
    }
}
