package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Test;

import bank.ConsoleStatementPrinter;
import bank.InMemoryTransactions;
import bank.StatementPrinter;
import bank.SystemDate;
import bank.Transaction;
import bank.TransactionPrinter;
import bank.TransactionSet;
import bank.Transactions;

public class ConsoleStatementPrinterShould {

	private static final TransactionPrinter UNUSED_TRANSACTION_PRINTER = null;

	@Test
	public void asks_the_repository_to_get_transactions() {
		final StatementPrinter statementPrinter = new ConsoleStatementPrinter(
				UNUSED_TRANSACTION_PRINTER);
		final Transactions transactions = mock(Transactions.class);
		when(transactions.getAll()).thenReturn(
		        new TransactionSet(new ArrayList()));

		statementPrinter.printStatementFor(transactions);

		verify(transactions).getAll();
	}

	@Test
	public void prints_each_transaction() {

		final TransactionPrinter transactionPrinter = mock(TransactionPrinter.class);
		final StatementPrinter statementPrinter = new ConsoleStatementPrinter(
				transactionPrinter);
		final SystemDate systemDate = mock(SystemDate.class);
		when(systemDate.now()).thenReturn(LocalDateTime.now());
		final Transactions transactions = new InMemoryTransactions(
				systemDate);
		transactions.register(100);
		transactions.register(-100);

		statementPrinter.printStatementFor(transactions);

		verify(transactionPrinter, times(2)).print(any(Transaction.class));
	}

}
