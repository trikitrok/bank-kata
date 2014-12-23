package bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionSet implements Iterable<Transaction>{

	private final List<Transaction> transactions;

	public TransactionSet(final List<Transaction> transactions) {
		this.transactions = new ArrayList<Transaction>(transactions);
	}

	@Override
	public Iterator<Transaction> iterator() {
		return transactions.iterator();
	}

}
