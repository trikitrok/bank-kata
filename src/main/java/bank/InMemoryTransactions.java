package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class InMemoryTransactions implements Transactions {

    private final List<Transaction> transactions;
    private final SystemDate systemDate;

    public InMemoryTransactions(final SystemDate systemDate) {
        transactions = new ArrayList<Transaction>();
        this.systemDate = systemDate;
    }

    @Override
    public void register(final int amount) {
        transactions.add(new Transaction(amount, systemDate.now()));
    }

    @Override
    public Boolean contains(final Transaction transaction) {
        return transactions.contains(transaction);
    }

    @Override
    public Statement generateStatement() {
        Stack<StatementLine> statementLinesInReverseOrder = new Stack<>();
        int balance = 0;
        for (final Transaction transaction : transactions) {
            balance += transaction.amount();
            statementLinesInReverseOrder.push(new StatementLine(transaction, balance));
        }

        List<StatementLine> statementLines = new ArrayList<StatementLine>();

        while (!statementLinesInReverseOrder.isEmpty()) {
            statementLines.add(statementLinesInReverseOrder.pop());
        }

        return new Statement(statementLines);
    }
}
