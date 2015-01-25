package unittests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static unittests.helpers.DateBuilder.date;
import static unittests.helpers.StatementBuilder.statement;
import static unittests.helpers.StatementLineBuilder.statementLine;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bank.accounts.statements.Statement;
import bank.accounts.statements.StatementLine;

public class StatementShould {
    @Test
    public void create_statement_with_statement_lines_in_reverse_transactions_order() {
        Statement expectedStatement = statement().withLines(statementLine(-50,
                                                                          date(11, 3, 2014),
                                                                          50),
                                                            statementLine(100,
                                                                          date(10, 3, 2014),
                                                                          100));

        List<StatementLine> statementLinesInTransactionsOrder = new ArrayList<StatementLine>();
        statementLinesInTransactionsOrder.add(statementLine(100, date(10, 3, 2014), 100));
        statementLinesInTransactionsOrder.add(statementLine(-50, date(11, 3, 2014), 50));

        Statement statement = Statement.create(statementLinesInTransactionsOrder);

        assertThat(statement, equalTo(expectedStatement));
    }
}
