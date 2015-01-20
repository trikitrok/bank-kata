package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public TransactionSet getAllInReversedAdditionOrder() {

        List<Transaction> transactionsInReversedAdditionOrder = new ArrayList<>(transactions);
        Collections.reverse(transactionsInReversedAdditionOrder);
        return new TransactionSet(transactionsInReversedAdditionOrder);
    }

    @Override
    public TransactionSet getAllInAdditionOrder() {
        return new TransactionSet(transactions);
    }
}
