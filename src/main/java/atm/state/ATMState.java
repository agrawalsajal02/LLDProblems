package atm.state;

import atm.ATM;
import atm.Card;
import atm.TransactionType;

public abstract class ATMState {
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException("Insert card is not allowed in state " + name());
    }

    public void authenticatePin(ATM atm, int pin) {
        throw new IllegalStateException("PIN authentication is not allowed in state " + name());
    }

    public void selectOperation(ATM atm, TransactionType transactionType) {
        throw new IllegalStateException("Operation selection is not allowed in state " + name());
    }

    public void checkBalance(ATM atm) {
        throw new IllegalStateException("Balance check is not allowed in state " + name());
    }

    public void withdrawCash(ATM atm, int amount) {
        throw new IllegalStateException("Cash withdrawal is not allowed in state " + name());
    }

    public void ejectCard(ATM atm) {
        atm.clearInsertedCard();
        atm.setCurrentState(new IdleState());
        System.out.println("Please collect your card");
    }

    protected String name() {
        return getClass().getSimpleName();
    }
}
