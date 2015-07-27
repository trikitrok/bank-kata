package bank.accounts.statements;

import java.util.Iterator;
import java.util.List;

public class Statement implements Iterable<StatementLine> {

    private final List<StatementLine> statementLines;

    public Statement(List<StatementLine> statementLines) {
        this.statementLines = statementLines;
    }

    public boolean isEmpty() {
        return this.statementLines.isEmpty();
    }

    @Override
    public Iterator<StatementLine> iterator() {
        return this.statementLines.iterator();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((statementLines == null) ? 0 : statementLines.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Statement other = (Statement) obj;
        if (statementLines == null) {
            if (other.statementLines != null)
                return false;
        } else if (!statementLines.equals(other.statementLines))
            return false;
        return true;
    }
}
