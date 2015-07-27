package bank.accounts.transactions;

import java.util.ArrayList;
import java.util.List;

import bank.accounts.statements.Statement;
import bank.accounts.statements.StatementGenerator;
import bank.system.SystemDate;

public class InMemoryTransactions implements Transactions {

    private final List<Transaction> transactions;
    private final SystemDate systemDate;
    private final StatementGenerator statementGenerator;

    public InMemoryTransactions(final SystemDate systemDate, StatementGenerator statementGenerator) {
        this.statementGenerator = statementGenerator;
        this.transactions = new ArrayList<>();
        this.systemDate = systemDate;
    }

    @Override
    public void recordTransactionOf(final float amount) {
        transactions.add(new Transaction(amount, systemDate.now()));
    }

    @Override
    public Statement statement() {
        final float INITIAL_BALANCE = 0;
        return statementGenerator.generateStatementFor(transactions, INITIAL_BALANCE);
    }

    public Boolean hasAlreadyRecorded(final Transaction transaction) {
        return transactions.contains(transaction);
    }
}
