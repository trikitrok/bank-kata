package bank;

public interface Transactions {

    void recordTransactionOf(float amount);

    Statement generateStatement();
}
