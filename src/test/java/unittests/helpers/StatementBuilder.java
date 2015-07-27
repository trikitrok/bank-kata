package unittests.helpers;

import java.util.Arrays;

import bank.accounts.statements.Statement;
import bank.accounts.statements.StatementLine;

public class StatementBuilder {

    public Statement withLines(StatementLine... statementLines) {
        return new Statement(Arrays.asList(statementLines));
    }

    public static StatementBuilder statement() {
        return new StatementBuilder();
    }
}
