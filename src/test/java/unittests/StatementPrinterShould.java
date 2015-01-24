package unittests;

import bank.*;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class StatementPrinterShould {

    @Test
    public void print_the_statement_directly() {
        List<StatementLine> statementLines = new ArrayList<>();
        statementLines.add(new StatementLine(new Transaction(500, new Date(114, 3, 10)), 1400));
        statementLines.add(new StatementLine(new Transaction(-100, new Date(114, 3, 2)), 900));
        statementLines.add(new StatementLine(new Transaction(1000, new Date(114, 3, 1)), 1000));
        Statement statement = new Statement(statementLines);
        Console console = mock(Console.class);
        InOrder inOrder = inOrder(console);
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(console, new StatementLineFormatter());

        statementPrinter.print(statement);

        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
