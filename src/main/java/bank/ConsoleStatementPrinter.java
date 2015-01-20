package bank;

public class ConsoleStatementPrinter implements StatementPrinter {

	private final StatementLineFormatter statementLineFormatter;

	public ConsoleStatementPrinter(
			final StatementLineFormatter statementLineFormatter) {
		this.statementLineFormatter = statementLineFormatter;
	}

	@Override
	public void printStatementFor(final Transactions transactions) {
		final TransactionSet transactionSet = transactions.getAll();

		for (final Transaction transaction : transactionSet) {
			statementLineFormatter.print(new StatementLine(transaction, 0));
		}
	}

}
