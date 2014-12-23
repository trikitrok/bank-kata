package bank;

public class ConsoleStatementPrinter implements StatementPrinter {

	private final TransactionPrinter transactionPrinter;

	public ConsoleStatementPrinter(final TransactionPrinter transactionPrinter) {
		this.transactionPrinter = transactionPrinter;
	}

	@Override
	public void printStatementFor(final Transactions transactions) {
		final TransactionSet transactionSet = transactions.getAll();

		for (final Transaction transaction : transactionSet) {
			transactionPrinter.print(transaction);
		}
	}

}
