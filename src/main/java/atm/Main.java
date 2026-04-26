package atm;

public final class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(new CashInventory(4, 10, 20));
        Card card = new Card("1234", 1111, new BankAccount(12_000));

        atm.insertCard(card);
        atm.authenticatePin(1111);
        atm.selectOperation(TransactionType.CHECK_BALANCE);
        atm.checkBalance();

        atm.insertCard(card);
        atm.authenticatePin(1111);
        atm.selectOperation(TransactionType.WITHDRAW_CASH);
        atm.withdrawCash(3_500);
    }
}
