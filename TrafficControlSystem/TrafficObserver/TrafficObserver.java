package TrafficControlSystem.TrafficObserver;

import TrafficControlSystem.IntersectionController;
import TrafficControlSystem.IntersectionStates.IntersectionState;
import TrafficControlSystem.Light;
import TrafficControlSystem.TrafficLights;

public interface TrafficObserver {

    public void show (TrafficLights ctx);
}
