package atm.state;

import atm.ATM;
import atm.Card;
import atm.WithdrawalBreakdown;

public final class CashWithdrawalState extends ATMState {
    @Override
    public void withdrawCash(ATM atm, int amount) {
        Card card = atm.getInsertedCard();
        if (card == null) {
            throw new IllegalStateException("No card inserted");
        }
        if (!card.getBankAccount().hasFunds(amount)) {
            System.out.println("Insufficient account balance");
            ejectCard(atm);
            return;
        }

        WithdrawalBreakdown breakdown = atm.getCashDispenser().dispense(atm.getCashInventory(), amount);
        card.getBankAccount().debit(amount);
        System.out.println("Please collect cash: " + breakdown);
        ejectCard(atm);
    }
}
