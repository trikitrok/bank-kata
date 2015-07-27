package unittests;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static unittests.helpers.DateFactory.date;
import static unittests.helpers.StatementBuilder.statement;
import static unittests.helpers.StatementFactory.statementLine;

import org.junit.Test;
import org.mockito.InOrder;

import bank.accounts.statements.Statement;
import bank.accounts.statements.printing.ConsoleStatementPrinter;
import bank.accounts.statements.printing.StatementLineFormatter;
import bank.accounts.statements.printing.StatementPrinter;
import bank.system.Console;

public class StatementPrinterShould {

    @Test
    public void print_a_formatted_statement_to_the_console() {
        Statement statement = statement().withLines(
                statementLine(500, date(10, 4, 2014), 1400),
                statementLine(-100, date(2, 4, 2014), 900),
                statementLine(1000, date(1, 4, 2014), 1000)
        );
        Console console = mock(Console.class);
        InOrder inOrder = inOrder(console);
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(
                console, new StatementLineFormatter()
        );

        statementPrinter.print(statement);

        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
