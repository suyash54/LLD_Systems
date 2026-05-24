package TrafficControlSystem.LightStates;

import TrafficControlSystem.Light;
import TrafficControlSystem.TrafficLights;

public interface State {

    public void handle(TrafficLights light);

    public Light getLight();
}
