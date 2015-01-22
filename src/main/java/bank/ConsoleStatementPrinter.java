package bank;

import java.util.Stack;

public class ConsoleStatementPrinter implements StatementPrinter {

    private final StatementLineFormatter statementLineFormatter;
    private Console console;

    public ConsoleStatementPrinter(final Console console,
                                   final StatementLineFormatter statementLineFormatter) {
        this.statementLineFormatter = statementLineFormatter;
        this.console = console;
    }

    @Override
    public void printStatementLine(StatementLine statementLine) {
        console.printLine(statementLineFormatter.format(statementLine));
    }

    @Override
    public void printHeader() {
        console.printLine("DATE | AMOUNT | BALANCE");
    }

}
