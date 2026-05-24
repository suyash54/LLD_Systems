package TrafficControlSystem;

import TrafficControlSystem.LightStates.GreenState;
import TrafficControlSystem.LightStates.RedState;
import TrafficControlSystem.LightStates.State;

public class TrafficLights {

    Direction direction;
    Light light;
    State currentState;
    State nextState;
    int intersectionId;

    public TrafficLights(Direction direction,int intersectionId){
        this.direction = direction;
        this.intersectionId = intersectionId;
        this.light = Light.RED;
        this.currentState = new RedState();
        this.nextState = new RedState();
    }

    public void setColor(Light light){
        this.light = light;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void setNextState(State state){
        this.nextState = state;
    }

    public void startGreen(){
        this.currentState = new GreenState();
        this.currentState.handle(this);
    }

    public void transition(){
        this.nextState = this.currentState;
        this.currentState.handle(this);
    }

    public int  getIntersectionId(){
        return intersectionId;
    }

    public State getCurrentState(){
        return this.currentState;
    }

    public State getNextState(){
        return this.nextState;
    }

    public Light getLight(){
        return this.light;
    }

}
