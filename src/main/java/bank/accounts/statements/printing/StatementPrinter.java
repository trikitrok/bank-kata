package bank.accounts.statements.printing;

import bank.accounts.statements.Statement;

public interface StatementPrinter {
    void print(Statement statement);
}
