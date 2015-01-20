package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.mockito.InOrder;

import bank.Console;
import bank.ConsoleStatementPrinter;
import bank.InMemoryTransactions;
import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.StatementPrinter;
import bank.SystemDate;
import bank.Transaction;
import bank.TransactionSet;
import bank.Transactions;

public class ConsoleStatementPrinterShould {

    @Test
    public void ask_the_repository_to_get_transactions() {
        final TransactionSet emptyTransactionSet = new TransactionSet(new ArrayList<Transaction>());
        final StatementLineFormatter notCalledStatementLineFormatter = null;
        final Console notCalledConsole = null;

        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(notCalledConsole,
                notCalledStatementLineFormatter);
        final Transactions transactions = mock(Transactions.class);

        when(transactions.getAllInAdditionOrder()).thenReturn(emptyTransactionSet);

        statementPrinter.printStatementFor(transactions);

        verify(transactions).getAllInAdditionOrder();
    }

    @Test
    public void print_each_transaction() {

        final StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);

        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(mock(Console.class),
                statementLineFormatter);
        final SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(new Date());
        final Transactions transactions = new InMemoryTransactions(systemDate);
        transactions.register(100);
        transactions.register(-100);

        statementPrinter.printStatementFor(transactions);

        verify(statementLineFormatter, times(2)).print(any(StatementLine.class));
    }

    @Test
    public void compute_balance() {

        final StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(mock(Console.class),
                statementLineFormatter);
        final SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(new Date(114, 11, 1));
        final Transactions transactions = new InMemoryTransactions(systemDate);
        transactions.register(100);
        transactions.register(-100);
        InOrder inOrder = inOrder(statementLineFormatter);

        statementPrinter.printStatementFor(transactions);

        inOrder.verify(statementLineFormatter).print(
                new StatementLine(new Transaction(-100, new Date(114, 11, 1)), 0));

        inOrder.verify(statementLineFormatter).print(
                new StatementLine(new Transaction(100, new Date(114, 11, 1)), 100));
    }
}
