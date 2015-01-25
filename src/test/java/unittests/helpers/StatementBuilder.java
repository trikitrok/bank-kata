package unittests.helpers;

import java.util.Arrays;

import bank.Statement;
import bank.StatementLine;

public class StatementBuilder {
    public Statement withLines(StatementLine... statementLines) {
        return new Statement(Arrays.asList(statementLines));
    }

    public static StatementBuilder aStatement() {
        return new StatementBuilder();
    }
}
