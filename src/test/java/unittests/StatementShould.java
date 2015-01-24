package unittests;

import bank.Statement;
import bank.StatementLine;
import bank.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class StatementShould {
	@Test
	public void create_statement_with_statement_lines_in_reverse_transactions_order() {
		List<StatementLine> statementLines = new ArrayList<StatementLine>();
		statementLines.add(new StatementLine(new Transaction(-50, new Date(114, 2, 11)), 50));
		statementLines.add(new StatementLine(new Transaction(100, new Date(114, 2, 10)), 100));
		Statement expectedStatement = new Statement(statementLines);

		List<StatementLine> statementLinesInTransactionsOrder = new ArrayList<StatementLine>();
		statementLinesInTransactionsOrder.add(new StatementLine(new Transaction(100, new Date(114, 2, 10)), 100));
		statementLinesInTransactionsOrder.add(new StatementLine(new Transaction(-50, new Date(114, 2, 11)), 50));


		System.out.println("before = " + statementLinesInTransactionsOrder);

		Statement statement = Statement.create(statementLinesInTransactionsOrder);
		System.out.println("after = " + statementLinesInTransactionsOrder);

		assertThat(statement, equalTo(expectedStatement));
	}


}
