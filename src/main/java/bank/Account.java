package bank;

public class Account {

    private final Transactions transactions;
    private final StatementPrinter statementPrinter;

    public Account(final StatementPrinter statementPrinter, final Transactions transactions) {
        this.transactions = transactions;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(final float amount) {
        transactions.register(amount);
    }

    public void withdraw(final float amount) {
        transactions.register(-amount);
    }

    public void printStatement() {
        statementPrinter.print(transactions.generateStatement());
    }

}
