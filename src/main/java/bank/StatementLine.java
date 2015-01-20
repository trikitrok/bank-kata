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
        return transaction.amount();
    }

    public Date date() {
        return transaction.date();
    }

    public int balance() {
        return balance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + balance;
        result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StatementLine other = (StatementLine) obj;
        if (balance != other.balance)
            return false;
        if (transaction == null) {
            if (other.transaction != null)
                return false;
        } else if (!transaction.equals(other.transaction))
            return false;
        return true;
    }

}
