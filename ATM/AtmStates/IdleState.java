package ATM.AtmStates;

import ATM.Atm;
import ATM.Card;

public class IdleState extends State{

    public void insertCard(Atm atm, Card card){
        System.out.println("Card has been inserted.");
        atm.setCurrentAtmState(new HasCardState());
    }
}
