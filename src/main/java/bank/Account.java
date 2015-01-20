package bank;

public class Account {

    private final Transactions transactions;
    private final StatementPrinter statementPrinter;

    public Account(final StatementPrinter statementPrinter, final Transactions transactions) {
        this.transactions = transactions;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(final int amount) {
        transactions.register(amount);
    }

    public void withdraw(final int amount) {
        transactions.register(-amount);
    }

    public void printStatement() {
        // TODO: Demeter Law?
        Statement statement = transactions.generateStatement();
        statement.print(statementPrinter);
    }

}
