package bank;

public interface Transactions {

    void register(int amount);

    Statement generateStatement();
}
