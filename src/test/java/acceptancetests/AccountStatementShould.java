package acceptancetests;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static unittests.helpers.DateFactory.date;

import bank.accounts.statements.StatementGenerator;
import org.junit.Test;
import org.mockito.InOrder;

import bank.accounts.Account;
import bank.accounts.statements.printing.ConsoleStatementPrinter;
import bank.accounts.statements.printing.StatementLineFormatter;
import bank.accounts.transactions.InMemoryTransactions;
import bank.system.Console;
import bank.system.SystemDate;

public class AccountStatementShould {

    @Test
    public void have_the_following_format() {
        Console console = mock(Console.class);
        InOrder inOrder = inOrder(console);
        SystemDate systemDate = mock(SystemDate.class);
        when(systemDate.now())
                .thenReturn(date(1, 4, 2014))
                .thenReturn(date(2, 4, 2014))
                .thenReturn(date(10, 4, 2014));
        Account account = new Account(
                new ConsoleStatementPrinter(console, new StatementLineFormatter()),
                new InMemoryTransactions(systemDate, new StatementGenerator())
        );
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
