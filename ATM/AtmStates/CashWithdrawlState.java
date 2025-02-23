package ATM.AtmStates;

import ATM.Atm;
import ATM.Card;
import ATM.Withdrawl.CashWithdrawl;
import ATM.Withdrawl.FiveHundredWithdrawl;
import ATM.Withdrawl.OneHundredWithdrawl;
import ATM.Withdrawl.TwoThousanNotes;

public class CashWithdrawlState extends State {

    public CashWithdrawlState() {
        System.out.println("Enter Cash Withdrawl State");
    }

    public void cashWithdrawl(Atm atm, Card card, int withdrawlAmount) {
        if (withdrawlAmount > atm.getAtmBalance()) {
            System.out.println("Insufficient balance in atm");
            exit(atm);
        } else if (card.getBalance() < withdrawlAmount) {
            System.out.println("Insufficient amount in your card");
        } else {
            card.deductAmount(withdrawlAmount);
            atm.deductAtmBalance(withdrawlAmount);


            CashWithdrawl cashWithdrawl = new TwoThousanNotes(new FiveHundredWithdrawl(new OneHundredWithdrawl(null)));

            cashWithdrawl.withdraw(atm,withdrawlAmount);
            exit(atm);
        }
    }

    public void exit(Atm atm){
        atm.setCurrentAtmState(new IdleState());
        System.out.println("Thank you for using our service !!");
    }
}
