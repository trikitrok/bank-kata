package unittests.helpers;

import java.util.Date;

import bank.accounts.statements.StatementLine;
import bank.accounts.transactions.Transaction;

public class StatementFactory {

    public static StatementLine statementLine(float amount, Date date, float balance) {
        return new StatementLine(new Transaction(amount, date), balance);
    }
}
