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
    public void printStatementFor(final Transactions transactions) {
        final TransactionSet transactionSet = transactions.getAllInAdditionOrder();

        boolean printHeader = false;
        for (Transaction current : transactionSet) {
            printHeader = true;
        }
        if (printHeader) {
            console.printLine("DATE | AMOUNT | BALANCE");
        }

        Stack<StatementLine> statementLinesInReverseOrder = new Stack<>();
        int balance = 0;
        for (final Transaction transaction : transactionSet) {
            balance += transaction.amount();
            statementLinesInReverseOrder.push(new StatementLine(transaction, balance));
        }

        while (!statementLinesInReverseOrder.isEmpty()) {
            StatementLine currentStatement = statementLinesInReverseOrder.pop();
            console.printLine(statementLineFormatter.print(currentStatement));
        }
    }

}
