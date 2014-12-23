package bank;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactions implements Transactions {

	private final List<Transaction> transactions;
	private SystemDate systemDate;

	public InMemoryTransactions(SystemDate systemDate) {
		transactions = new ArrayList<Transaction>();
		this.systemDate = systemDate;
	}

	public void register(int amount) {
		transactions.add(new Transaction(amount, systemDate.now()));
	}

	@Override
	public Boolean contains(Transaction transaction) {
		return transactions.contains(transaction);
	}

}
