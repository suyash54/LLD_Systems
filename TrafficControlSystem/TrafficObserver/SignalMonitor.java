package TrafficControlSystem.TrafficObserver;

import TrafficControlSystem.IntersectionController;
import TrafficControlSystem.IntersectionStates.IntersectionState;
import TrafficControlSystem.Light;
import TrafficControlSystem.TrafficLights;

public class SignalMonitor implements TrafficObserver{

    @Override
    public void show(TrafficLights ctx){

        System.out.println("-----"+ctx.getDirection()+" is currently in "+ctx.getLight().toString()+"----- state---------");
    }

}