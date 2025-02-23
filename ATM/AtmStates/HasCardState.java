package ATM.AtmStates;

import ATM.Atm;
import ATM.Card;

public class HasCardState extends State {

    HasCardState(){
        System.out.println("Card has been inserted");
    }

    public void authenticatePin(Atm atm, Card card, int pin){
        boolean isPinCorrect = card.isPinCorrect(pin);
        if(!isPinCorrect){
            System.out.println("Entered Pin is invalid");
            exit(atm);
        }
        else{
            atm.setCurrentAtmState(new SelectOperationState());
        }
    }

    public void exit(Atm atm){
        System.out.println("Thank you for using our service.");
        atm.setCurrentAtmState(new IdleState());
    }
}
