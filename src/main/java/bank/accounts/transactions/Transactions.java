package bank.accounts.transactions;

import bank.accounts.statements.Statement;

public interface Transactions {
    void recordTransactionOf(float amount);

    Statement statement();
}
