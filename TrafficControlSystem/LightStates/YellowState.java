package TrafficControlSystem.LightStates;

import TrafficControlSystem.Light;
import TrafficControlSystem.TrafficLights;

public class YellowState implements State{


    Light light;

    public void handle(TrafficLights light){
        light.setColor(Light.YELLOW);
        this.light = Light.YELLOW;
        light.setNextState(new RedState());
    }

    public Light getLight(){
        return this.light;
    }
}
