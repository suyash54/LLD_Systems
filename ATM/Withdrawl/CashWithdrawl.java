package ATM.Withdrawl;

import ATM.Atm;

public abstract  class CashWithdrawl {

    CashWithdrawl nextCashWithdrawl;

    CashWithdrawl(CashWithdrawl nextCashWithdrawl){
        this.nextCashWithdrawl = nextCashWithdrawl;
    }

    public void withdraw(Atm atm, int withdrawlAmount){

        if(nextCashWithdrawl!=null){
            nextCashWithdrawl.withdraw(atm,withdrawlAmount);
        }
    }
}
