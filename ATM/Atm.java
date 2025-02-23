package ATM;

import ATM.AtmStates.IdleState;
import ATM.AtmStates.State;

public class Atm {

    State atmState;
    int atmBalance;
    int numberOf100Notes;
    int numberOf500Notes;
    int numberOf2000Notes;

    private static Atm atmObject = new Atm();


    public void setCurrentAtmState(State atmState){
        this.atmState = atmState;
    }

    public State getCurrentAtmState(){
        return this.atmState;
    }

    public static Atm getAtmObject(){
        atmObject.setCurrentAtmState(new IdleState());
        return atmObject;
    }

    public int getAtmBalance(){
        return atmBalance;
    }

    public void setAtmBalance(int atmBalance, int numberOf100Notes, int numberOf500Notes, int numberOf2000Notes){
        this.atmBalance = atmBalance;
        this.numberOf100Notes = numberOf100Notes;
        this.numberOf500Notes = numberOf500Notes;
        this.numberOf2000Notes = numberOf2000Notes;
    }

    public int getNumberOf100Notes(){
        return this.numberOf100Notes;
    }

    public int getNumberOf500Notes(){
        return this.numberOf500Notes;
    }

    public int getNumberOf2000Notes(){
        return this.numberOf2000Notes;
    }

    public void deductAtmBalance(int amount){
        atmBalance = atmBalance - amount;
    }

    public void deduct100notes(int number){
        numberOf100Notes = numberOf100Notes - number;
    }

    public void deduct500Notes(int number){
        numberOf500Notes = numberOf500Notes - number;
    }

    public void deduct2000Notes(int number){
        numberOf2000Notes = numberOf2000Notes - number;
    }

    public void printCurrentAtmBalance(){
        System.out.println("Current Atm balance is : ");
        System.out.println("Balance"+atmBalance);

    }

}
