package TrafficControlSystem.LightStates;

import TrafficControlSystem.Light;
import TrafficControlSystem.TrafficLights;

public class RedState implements State{

    Light light;

    public void handle(TrafficLights light){
        light.setColor(Light.RED);
        this.light = Light.RED;
        light.setNextState(new RedState());
    }

    public Light getLight(){
        return this.light;
    }
}
