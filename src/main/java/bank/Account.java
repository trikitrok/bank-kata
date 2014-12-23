package bank;


public class Account {

    private final Transactions transactions;
    private StatementPrinter statementPrinter;

    public Account(StatementPrinter statementPrinter, Transactions transactions) {
        this.transactions = transactions;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        transactions.register(amount);
    }

    public void withdraw(int amount) {
        transactions.register(-amount);
    }

    public void printStatement() {
        statementPrinter.printStatementFor(transactions);
    }

}
