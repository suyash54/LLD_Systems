package ATM.AtmStates;

import ATM.Atm;
import ATM.Card;

public class CheckBalanceState extends State{

    CheckBalanceState(){
        System.out.println("Here is your balance !!");
    }

    public void showBalance(Atm atm, Card card){
           int balance = card.getBalance();
           System.out.println("Balance is"+balance);
           exit(atm);
    }

    public void exit(Atm atm){
        atm.setCurrentAtmState(new IdleState());
        System.out.println("Thank you for using our service.");
    }
}
