package unittests;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bank.Statement;
import bank.StatementLine;
import bank.StatementPrinter;
import bank.SystemDate;
import bank.Transaction;

public class StatementPrinterShould {

    @Test
    public void delegate_printing_to_statementPrinter_when_it_does_have_statementLines() {
        StatementPrinter statementPrinter = mock(StatementPrinter.class);
        List<StatementLine> statementLines = new ArrayList<StatementLine>();
        statementLines.add(new StatementLine(new Transaction(-50, new SystemDate().now()), 50));
        statementLines.add(new StatementLine(new Transaction(100, new SystemDate().now()), 100));

        Statement statement = new Statement(statementLines);

        statement.print(statementPrinter);

        verify(statementPrinter).printHeader();
        verify(statementPrinter).printStatementLine(statementLines.get(0));
        verify(statementPrinter).printStatementLine(statementLines.get(1));
    }

    @Test
    public void delegate_printing_to_statementPrinter_when_it_has_no_statementLines() {
        StatementPrinter statementPrinter = mock(StatementPrinter.class);
        List<StatementLine> statementLines = new ArrayList<StatementLine>();

        Statement statement = new Statement(statementLines);

        statement.print(statementPrinter);

        verify(statementPrinter, never()).printHeader();
        verify(statementPrinter, never()).printStatementLine(any(StatementLine.class));
    }
}
