package TrafficControlSystem.LightStates;

import TrafficControlSystem.Light;
import TrafficControlSystem.TrafficLights;

public class GreenState implements State{

    Light light;

    public void handle(TrafficLights light){
        light.setColor(Light.GREEN);
        this.light = Light.GREEN;
        light.setNextState(new YellowState());

    }

    public Light getLight(){
        return this.light;
    }
}
