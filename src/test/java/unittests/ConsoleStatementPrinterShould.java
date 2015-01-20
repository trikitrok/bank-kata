package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import bank.Console;
import bank.ConsoleStatementPrinter;
import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.StatementPrinter;
import bank.SystemDate;
import bank.Transaction;

public class ConsoleStatementPrinterShould {

    @Test
    public void print_the_header() {

        Console console = mock(Console.class);
        final StatementLineFormatter unusedStatementLinePrinter = null;

        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                unusedStatementLinePrinter);

        statementPrinter.printHeader();

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void ask_to_format_a_statement_line() {

        Console console = mock(Console.class);
        final StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);
        StatementLine statementLine = new StatementLine(
                new Transaction(100, new SystemDate().now()), 100);

        statementPrinter.printStatementLine(statementLine);

        verify(statementLineFormatter).format(statementLine);
    }

    @Test
    // This is a side effect - should fail when changing the formatting
    public void print_a_formatted_line_on_the_console() {
        final String anyFormattedStatementLine = "whatever";
        Console console = mock(Console.class);
        final StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        when(statementLineFormatter.format(any(StatementLine.class))).thenReturn(
                anyFormattedStatementLine);
        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);
        StatementLine statementLine = null;

        statementPrinter.printStatementLine(statementLine);

        verify(console).printLine(anyFormattedStatementLine);
    }
}
