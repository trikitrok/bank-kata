package bank.accounts.statements;

import bank.accounts.transactions.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatementGenerator {

    public Statement generateStatementFor(List<Transaction> transactions, float initialBalance) {
        List<StatementLine> statementLines = createStatementLinesFromTransactions(
                transactions, initialBalance
        );
        return new Statement(reverseStatementLinesOrder(statementLines));
    }

    private List<StatementLine> createStatementLinesFromTransactions(
            List<Transaction> transactions,
            float initialBalance
    ) {
        List<StatementLine> statementLines = new ArrayList<>();
        float balance = initialBalance;
        for (final Transaction transaction : transactions) {
            balance += transaction.amount();
            statementLines.add(new StatementLine(transaction, balance));
        }
        return statementLines;
    }

    private List<StatementLine> reverseStatementLinesOrder(
            List<StatementLine> statementLinesInTransactionsOrder
    ) {
        final ArrayList<StatementLine> reversedList = new ArrayList<>(statementLinesInTransactionsOrder);
        Collections.reverse(reversedList);
        return reversedList;
    }
}
