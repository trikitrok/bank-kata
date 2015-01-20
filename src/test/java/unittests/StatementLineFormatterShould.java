package unittests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.Transaction;

public class StatementLineFormatterShould {

	@Test
	public void print_a_single_formatted_transaction_on_the_console() {
		final LocalDateTime A_DATE = LocalDateTime.of(2014, 4, 10, 0, 0);
		final StatementLineFormatter statementLineFormatter = new StatementLineFormatter();

		assertThat(statementLineFormatter.print(new StatementLine(
				new Transaction(500, A_DATE), 1500)),
		        is("10/04/2014 | 500.00 | 1500.00"));
	}

}
