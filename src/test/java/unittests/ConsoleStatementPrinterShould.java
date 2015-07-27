package unittests;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static unittests.helpers.StatementBuilder.statement;
import static unittests.helpers.StatementFactory.statementLine;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import bank.accounts.statements.Statement;
import bank.accounts.statements.StatementLine;
import bank.accounts.statements.printing.ConsoleStatementPrinter;
import bank.accounts.statements.printing.StatementLineFormatter;
import bank.accounts.statements.printing.StatementPrinter;
import bank.system.Console;
import bank.system.SystemDate;

public class ConsoleStatementPrinterShould {

    private Console console;
    private StatementLineFormatter statementLineFormatter;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() throws Exception {
        console = mock(Console.class);
        statementLineFormatter = mock(StatementLineFormatter.class);
        statementPrinter = new ConsoleStatementPrinter(console, statementLineFormatter);
    }

    @Test
    public void format_statement_lines() {
        StatementLine statementLine = statementLine(100, new SystemDate().now(), 100);

        statementPrinter.print(new Statement(Arrays.asList(statementLine)));

        verify(statementLineFormatter).format(statementLine);
    }

    @Test
    public void print_a_formatted_line_on_the_console() {
        StatementLine statementLine = null;
        String formattedStatementLine = "whatever";
        when(statementLineFormatter
                .format(statementLine))
                .thenReturn(formattedStatementLine);

        statementPrinter.print(statement().withLines(statementLine));

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine(formattedStatementLine);
    }

    @Test
    public void neither_format_nor_print_on_the_console_when_there_are_no_statement_lines() {
        statementPrinter.print(statement().withLines());

        verify(statementLineFormatter, never()).format(any(StatementLine.class));
        verify(console, never()).printLine(anyString());
        verify(console, never()).printLine(anyString());
    }
}
