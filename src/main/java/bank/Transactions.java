package bank;

public interface Transactions {

    void register(float amount);

    Statement generateStatement();
}
