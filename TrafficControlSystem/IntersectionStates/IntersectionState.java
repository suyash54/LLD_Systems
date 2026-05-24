package TrafficControlSystem.IntersectionStates;

import TrafficControlSystem.IntersectionController;

public interface IntersectionState {

    public void handle(IntersectionController ctx) throws InterruptedException;
}
