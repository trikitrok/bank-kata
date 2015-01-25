package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static unittests.helpers.StatementBuilder.statement;
import static unittests.helpers.StatementLineBuilder.statementLine;

import java.util.Arrays;

import org.junit.Test;

import bank.Console;
import bank.ConsoleStatementPrinter;
import bank.Statement;
import bank.StatementLine;
import bank.StatementLineFormatter;
import bank.StatementPrinter;
import bank.SystemDate;

public class ConsoleStatementPrinterShould {

    @Test
    public void format_statement_lines() {
        Console console = mock(Console.class);
        final StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        final StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);
        StatementLine statementLine = statementLine(100, new SystemDate().now(), 100);

        statementPrinter.print(new Statement(Arrays.asList(statementLine)));

        verify(statementLineFormatter).format(statementLine);
    }

    @Test
    public void print_a_formatted_line_on_the_console() {
        StatementLine statementLine = null;
        String formattedStatementLine = "whatever";
        Console console = mock(Console.class);
        StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        when(statementLineFormatter.format(any(StatementLine.class)))
                .thenReturn(formattedStatementLine);
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);

        statementPrinter.print(statement().withLines(statementLine));

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine(formattedStatementLine);
    }

    @Test
    public void neither_format_nor_print_on_the_console_when_there_are_no_statement_lines() {
        Console console = mock(Console.class);
        StatementLineFormatter statementLineFormatter = mock(StatementLineFormatter.class);
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(console,
                statementLineFormatter);

        statementPrinter.print(statement().withLines());

        verify(statementLineFormatter, never()).format(any(StatementLine.class));
        verify(console, never()).printLine(anyString());
        verify(console, never()).printLine(anyString());
    }
}
