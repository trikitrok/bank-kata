package bank.accounts.transactions;

import java.util.ArrayList;
import java.util.List;

import bank.accounts.statements.Statement;
import bank.accounts.statements.StatementLine;
import bank.system.SystemDate;

public class InMemoryTransactions implements Transactions {

    private final List<Transaction> transactions;
    private final SystemDate systemDate;

    public InMemoryTransactions(final SystemDate systemDate) {
        transactions = new ArrayList<>();
        this.systemDate = systemDate;
    }

    @Override
    public void recordTransactionOf(final float amount) {
        transactions.add(new Transaction(amount, systemDate.now()));
    }

    @Override
    public Statement generateStatement() {
        return new StatementGenerator().generateStatement(transactions);
    }

    public Boolean hasAlreadyRegistered(final Transaction transaction) {
        return transactions.contains(transaction);
    }

    class StatementGenerator {
        public static final float INITIAL_BALANCE = 0;

        public Statement generateStatement(List<Transaction> transactions) {
            List<StatementLine> statementLines = getStatementLinesInTransactionsOrder(transactions);

            return Statement.create(statementLines);
        }

        private List<StatementLine> getStatementLinesInTransactionsOrder(List<Transaction> transactions) {
            List<StatementLine> statementLines = new ArrayList<>();
            float balance = INITIAL_BALANCE;
            for (final Transaction transaction : transactions) {
                balance += transaction.amount();
                statementLines.add(new StatementLine(transaction, balance));
            }
            return statementLines;
        }
    }
}
