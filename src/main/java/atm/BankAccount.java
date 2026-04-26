package atm;

public final class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public boolean hasFunds(int amount) {
        return balance >= amount;
    }

    public void debit(int amount) {
        if (!hasFunds(amount)) {
            throw new IllegalArgumentException("Insufficient account balance");
        }
        balance -= amount;
    }
}
