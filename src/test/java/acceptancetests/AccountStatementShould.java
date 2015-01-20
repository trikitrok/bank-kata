package acceptancetests;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Test;
import org.mockito.InOrder;

import bank.Account;
import bank.Console;
import bank.ConsoleStatementPrinter;
import bank.InMemoryTransactions;
import bank.StatementLineFormatter;
import bank.SystemDate;

public class AccountStatementShould {

    @Test
    public void have_the_following_format() {

        Console console = mock(Console.class);
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now()).thenReturn(new Date(114, 3, 1)).thenReturn(new Date(114, 3, 2))
                .thenReturn(new Date(114, 3, 10));

        Account account = new Account(new ConsoleStatementPrinter(console,
                new StatementLineFormatter()), new InMemoryTransactions(systemDate));

        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);
        // InOrder inOrder = inOrder(console);

        account.printStatement();

        // inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        // inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        // inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        // inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");

    }
}
