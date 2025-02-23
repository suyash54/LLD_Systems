package ATM.Withdrawl;

import ATM.Atm;

public class OneHundredWithdrawl extends CashWithdrawl {

    public OneHundredWithdrawl(CashWithdrawl nextCashWithdrawl) {
        super(nextCashWithdrawl);
    }

    public void withdrawl(Atm atm, int withdrawlAmount) {
        int required = withdrawlAmount / 100;
        int remaining = withdrawlAmount % 100;

        if (required > atm.getNumberOf100Notes()) {
            remaining = remaining + (required - atm.getNumberOf100Notes()) * 100;
            atm.deduct100notes(atm.getNumberOf100Notes());
        } else if (required <= atm.getNumberOf100Notes()) {
            atm.deduct100notes(required);
        }

        if(remaining!=0){
            super.withdraw(atm,remaining);
        }

    }
}
