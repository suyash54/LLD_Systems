package ATM.Withdrawl;

import ATM.Atm;

public class TwoThousanNotes extends CashWithdrawl {
    
    public TwoThousanNotes(CashWithdrawl nextCashWithdrawl){
        super(nextCashWithdrawl);
    }

    public void withdrawl(Atm atm, int withdrawlAmount) {
        int required = withdrawlAmount / 2000;
        int remaining = withdrawlAmount % 2000;

        if (required > atm.getNumberOf2000Notes()) {
            remaining = remaining + (required - atm.getNumberOf2000Notes()) * 2000;
            atm.deduct2000Notes(atm.getNumberOf2000Notes());
        } else if (required <= atm.getNumberOf2000Notes()) {
            atm.deduct2000Notes(required);
        }

        if(remaining!=0){
            super.withdraw(atm,remaining);
        }

    }
}
