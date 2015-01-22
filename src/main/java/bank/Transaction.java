package bank;

import java.util.Date;

public class Transaction {
    private final int amount;
    private final Date date;

    public Transaction(final int amount, final Date date) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
        result = prime * result + (date == null ? 0 : date.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (amount != other.amount) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        return true;
    }

    public int amount() {
        return amount;
    }

    public Date date() {
        return date;
    }
}
