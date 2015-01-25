package bank;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactions implements Transactions {

    public static final float INITIAL_BALANCE = 0;
    private final List<Transaction> transactions;
    private final SystemDate systemDate;

    public InMemoryTransactions(final SystemDate systemDate) {
        transactions = new ArrayList<Transaction>();
        this.systemDate = systemDate;
    }

    @Override
    public void register(final float amount) {
        transactions.add(new Transaction(amount, systemDate.now()));
    }

    @Override
    public Statement generateStatement() {
        List<StatementLine> statementLines = getStatementLinesInTransactionsOrder();

        return Statement.create(statementLines);
    }

    public Boolean hasAlreadyRegistered(final Transaction transaction) {
        return transactions.contains(transaction);
    }

    private List<StatementLine> getStatementLinesInTransactionsOrder() {
        List<StatementLine> statementLines = new ArrayList<>();
        float balance = INITIAL_BALANCE;
        for (final Transaction transaction : transactions) {
            balance += transaction.amount();
            statementLines.add(new StatementLine(transaction, balance));
        }
        return statementLines;
    }
}
