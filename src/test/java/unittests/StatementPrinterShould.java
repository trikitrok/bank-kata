package unittests;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Test;
import org.mockito.InOrder;

import static unittests.helpers.StatementBuilder.aStatement;
import bank.Console;
import bank.ConsoleStatementPrinter;
import bank.Statement;
import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.StatementPrinter;
import bank.Transaction;

public class StatementPrinterShould {

    @Test
    public void print_a_formatted_statement_to_the_console() {
        Statement statement = aStatement().withLines(
                new StatementLine(new Transaction(500, new Date(114, 3, 10)), 1400),
                new StatementLine(new Transaction(-100, new Date(114, 3, 2)), 900),
                new StatementLine(new Transaction(1000, new Date(114, 3, 1)), 1000));

        Console console = mock(Console.class);
        InOrder inOrder = inOrder(console);
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                new StatementLineFormatter());

        statementPrinter.print(statement);

        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
