package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import bank.Console;
import bank.ConsoleStatementPrinter;
import bank.Statement;
import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.StatementPrinter;
import bank.SystemDate;
import bank.Transaction;

public class ConsoleStatementPrinterShould {

    @Test
    public void ask_to_format_a_statement_line() {

        Console console = mock(Console.class);
        final StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);
        StatementLine statementLine = new StatementLine(
                new Transaction(100, new SystemDate().now()), 100);

        statementPrinter.print(new Statement(Arrays.asList(statementLine)));

        verify(statementLineFormatter).format(statementLine);
    }

    @Test
    // This is a side effect - should fail when changing the formatting
    public void print_a_formatted_line_on_the_console() {
        final String header = "DATE | AMOUNT | BALANCE";
        final String formattedStatementLine = "whatever";
        Console console = mock(Console.class);
        final StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        when(statementLineFormatter.format(any(StatementLine.class))).thenReturn(
                formattedStatementLine);
        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);
        StatementLine statementLine = null;

        statementPrinter.print(new Statement(Arrays.asList(statementLine)));

        verify(console).printLine(header);
        verify(console).printLine(formattedStatementLine);
    }
}
