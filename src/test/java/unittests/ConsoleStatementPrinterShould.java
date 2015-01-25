package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static unittests.helpers.StatementBuilder.statement;

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
    public void print_a_formatted_line_on_the_console() {
        final String header = "DATE | AMOUNT | BALANCE";
        final String formattedStatementLine = "whatever";
        Console console = mock(Console.class);
        StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        when(statementLineFormatter.format(any(StatementLine.class)))
                .thenReturn(formattedStatementLine);
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);
        StatementLine statementLine = null;
        Statement statement = statement().withLines(statementLine);

        statementPrinter.print(statement);

        verify(console).printLine(header);
        verify(console).printLine(formattedStatementLine);
    }

    @Test
    public void not_print_on_the_console_when_there_are_no_lines() {
        final String header = "DATE | AMOUNT | BALANCE";
        final String formattedStatementLine = "whatever";
        Console console = mock(Console.class);
        StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        when(statementLineFormatter.format(any(StatementLine.class)))
                .thenReturn(formattedStatementLine);
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);
        Statement statement = statement().withLines();

        statementPrinter.print(statement);

        verify(console, never()).printLine(header);
        verify(console, never()).printLine(formattedStatementLine);
    }
}
