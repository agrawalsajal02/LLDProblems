package atm.state;

import atm.ATM;
import atm.TransactionType;

public final class SelectOperationState extends ATMState {
    @Override
    public void selectOperation(ATM atm, TransactionType transactionType) {
        // Yahan se state branch hoti hai: user balance dekhna chahta hai ya cash nikalna chahta hai.
        switch (transactionType) {
            case CHECK_BALANCE:
                atm.setCurrentState(new CheckBalanceState());
                System.out.println("Balance check selected.");
                break;
            case WITHDRAW_CASH:
                atm.setCurrentState(new CashWithdrawalState());
                System.out.println("Cash withdrawal selected.");
                break;
            default:
                throw new IllegalArgumentException("Unsupported transaction type");
        }
    }
}
