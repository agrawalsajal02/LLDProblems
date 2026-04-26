package atm.state;

import atm.ATM;
import atm.Card;

public final class HasCardState extends ATMState {
    @Override
    public void authenticatePin(ATM atm, int pin) {
        Card card = atm.getInsertedCard();
        if (card == null) {
            throw new IllegalStateException("No card inserted");
        }
        if (!card.matchesPin(pin)) {
            System.out.println("Invalid PIN");
            ejectCard(atm);
            return;
        }

        atm.setCurrentState(new SelectOperationState());
        System.out.println("PIN verified. Please select operation.");
    }
}
