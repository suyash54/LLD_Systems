package ATM.AtmStates;

import ATM.Atm;
import ATM.Card;
import ATM.TransactionType;

public class SelectOperationState extends State{

    SelectOperationState(){
        System.out.println("Select your option cash withdrawl or check account\n");
    }

    public void selectOperation(Atm atm, Card card, TransactionType txnType){

        if(txnType == TransactionType.CASH_WITHDRAWAL){
            atm.setCurrentAtmState(new CashWithdrawlState());
        }
        else
        if(txnType == TransactionType.CHECK_BALANCE){
            atm.setCurrentAtmState(new CheckBalanceState());
        }
        else{
            System.out.println("Invalid option");
            exit(atm);
        }
    }

    public void exit(Atm atm){
        atm.setCurrentAtmState(new IdleState());
        System.out.println("Thank you for using our service");
    }

}
