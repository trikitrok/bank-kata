package unittests.helpers;

import bank.accounts.transactions.Transaction;

import java.util.Date;

public class TransactionFactory {

    public static Transaction transaction(float amount, Date date) {
        return new Transaction(amount, date);
    }
}
