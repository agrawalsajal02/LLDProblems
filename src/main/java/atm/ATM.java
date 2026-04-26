package atm;

import atm.state.ATMState;
import atm.state.IdleState;

public final class ATM {
    private final CashInventory cashInventory;
    private final CashDispenser cashDispenser;
    private ATMState currentState;
    private Card insertedCard;

    public ATM(CashInventory cashInventory) {
        this.cashInventory = cashInventory;
        this.cashDispenser = new CashDispenser();
        this.currentState = new IdleState();
    }

//    ATM khud logic nahi kar rahi.
//    ATM bas bol rahi hai:
//    “jo meri current state hai, usko ye action de do”
    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void authenticatePin(int pin) {
        currentState.authenticatePin(this, pin);
    }

    public void selectOperation(TransactionType transactionType) {
        currentState.selectOperation(this, transactionType);
    }

    public void checkBalance() {
        currentState.checkBalance(this);
    }

    public void withdrawCash(int amount) {
        currentState.withdrawCash(this, amount);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }

    public CashInventory getCashInventory() {
        return cashInventory;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public ATMState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public Card getInsertedCard() {
        return insertedCard;
    }

    public void setInsertedCard(Card insertedCard) {
        this.insertedCard = insertedCard;
    }

    public void clearInsertedCard() {
        this.insertedCard = null;
    }

    public int getAtmBalance() {
        return cashInventory.getTotalCash();
    }
}
