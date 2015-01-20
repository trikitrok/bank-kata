package bank;

import java.util.Date;

public class StatementLine {

    private final Transaction transaction;
    private final int balance;

    public StatementLine(final Transaction transaction, final int balance) {
        this.transaction = transaction;
        this.balance = balance;
    }

    public int amount() {
        // TODO Auto-generated method stub
        return transaction.amount();
    }

    public Date date() {
        return transaction.date();
    }

    public int balance() {
        return balance;
    }

}
