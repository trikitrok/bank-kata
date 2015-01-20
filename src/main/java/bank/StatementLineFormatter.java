package bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatementLineFormatter {

	private static final String FIELD_SEPARATOR = " | ";

	public String print(final StatementLine statementLine) {
		return formatDate(statementLine.date()) + FIELD_SEPARATOR
				+ formatMoney(statementLine.amount()) + FIELD_SEPARATOR
				+ formatMoney(statementLine.balance());
	}

	private String formatMoney(final int amount) {
		return amount + ".00";
	}

	private String formatDate(final LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
