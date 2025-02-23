package ATM;

import java.util.Date;

public class Card {

    int cardNumber;
    int cvv;
    Date expirayDate;
    String cardHolderName;
    static final int pin = 1234;
    private UserBankAccount userBankAccount;

    Card(int cardNumber, int cvv, Date expirayDate, String cardHolderName) {

        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirayDate = expirayDate;
        this.cardHolderName = cardHolderName;

    }

    public int getBalance() {
        return userBankAccount.balance;
    }

    public boolean isPinCorrect(int enteredPin) {
        return enteredPin == pin;
    }

    public void deductAmount(int withdrawAmount) {
        userBankAccount.withdrawAmount(withdrawAmount);
    }
}
