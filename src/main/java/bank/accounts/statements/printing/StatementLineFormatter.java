package bank.accounts.statements.printing;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import bank.accounts.statements.StatementLine;

public class StatementLineFormatter {

    private static final String FIELD_SEPARATOR = " | ";

    public String format(final StatementLine statementLine) {
        return formatDate(statementLine.date()) + FIELD_SEPARATOR
                + formatMoney(statementLine.amount()) + FIELD_SEPARATOR
                + formatMoney(statementLine.balance());
    }

    private String formatMoney(final float amount) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(amount);
    }

    private String formatDate(final Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
