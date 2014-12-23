package bank;

public interface Transactions {

    void register(int amount);

    Boolean contains(Transaction transaction);

}
