package atm.state;

import atm.ATM;
import atm.Card;

public final class IdleState extends ATMState {
    @Override
    public void insertCard(ATM atm, Card card) {
        // Idle state ka kaam card accept karna aur flow ko next state me le jana hai.
        atm.setInsertedCard(card);
        atm.setCurrentState(new HasCardState());
        System.out.println("Card inserted. Please enter PIN.");
    }
}
