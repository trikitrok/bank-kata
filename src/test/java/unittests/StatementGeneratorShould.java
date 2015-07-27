package unittests;

import bank.accounts.statements.Statement;
import bank.accounts.statements.StatementGenerator;
import bank.accounts.transactions.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static unittests.helpers.DateFactory.date;
import static unittests.helpers.StatementBuilder.statement;
import static unittests.helpers.StatementFactory.statementLine;
import static unittests.helpers.TransactionFactory.transaction;

public class StatementGeneratorShould {
    @Test
    public void create_a_statement_with_statement_lines_in_reverse_transactions_order() {
        final float INITIAL_BALANCE = 0;
        StatementGenerator generator = new StatementGenerator();

        Statement statement = generator.generateStatementFor(givenTransactionsList(), INITIAL_BALANCE);

        assertThat(statement, equalTo(
                statement().withLines(
                        statementLine(-50, date(11, 3, 2014), 50),
                        statementLine(100, date(10, 3, 2014), 100))));
    }

    private List<Transaction> givenTransactionsList() {
        List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.add(transaction(100, date(10, 3, 2014)));
        transactionsList.add(transaction(-50, date(11, 3, 2014)));
        return transactionsList;
    }
}
