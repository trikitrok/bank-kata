package bank;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatementLineFormatter {

    private static final String FIELD_SEPARATOR = " | ";

    public String format(final StatementLine statementLine) {
        return formatDate(statementLine.date()) + FIELD_SEPARATOR
                + formatMoney(statementLine.amount()) + FIELD_SEPARATOR
                + formatMoney(statementLine.balance());
    }

    private String formatMoney(final int amount) {
        return amount + ".00";
    }

    private String formatDate(final Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

}
