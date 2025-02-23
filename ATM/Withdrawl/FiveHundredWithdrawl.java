package ATM.Withdrawl;

import ATM.Atm;

public class FiveHundredWithdrawl extends CashWithdrawl{


    CashWithdrawl nextCashWithdrawl;

    public FiveHundredWithdrawl(CashWithdrawl nextCashWithdrawl){
       super(nextCashWithdrawl);
    }

    public void withdrawl(Atm atm, int withdrawlAmount) {
        int required = withdrawlAmount / 500;
        int remaining = withdrawlAmount % 500;

        if (required > atm.getNumberOf500Notes()) {
            remaining = remaining + (required - atm.getNumberOf500Notes()) * 500;
            atm.deduct500Notes(atm.getNumberOf500Notes());
        } else if (required <= atm.getNumberOf500Notes()) {
            atm.deduct500Notes(required);
        }

        if(remaining!=0){
            super.withdraw(atm,remaining);
        }

    }
}
