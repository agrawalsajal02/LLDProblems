package atm.state;

import atm.ATM;
import atm.Card;

public final class CheckBalanceState extends ATMState {
    @Override
    public void checkBalance(ATM atm) {
        Card card = atm.getInsertedCard();
        if (card == null) {
            throw new IllegalStateException("No card inserted");
        }
        System.out.println("Available balance: " + card.getBankAccount().getBalance());
        ejectCard(atm);
    }
}
