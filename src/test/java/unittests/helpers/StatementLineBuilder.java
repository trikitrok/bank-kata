package unittests.helpers;

import java.util.Date;

import bank.StatementLine;
import bank.Transaction;

public class StatementLineBuilder {
    public static StatementLine statementLine(float amount, Date date, float balance) {
        return new StatementLine(new Transaction(amount, date), balance);
    }
}
