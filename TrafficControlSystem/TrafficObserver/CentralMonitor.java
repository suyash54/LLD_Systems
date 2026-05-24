package TrafficControlSystem.TrafficObserver;

import TrafficControlSystem.IntersectionController;
import TrafficControlSystem.Light;
import TrafficControlSystem.TrafficLights;

public class CentralMonitor implements TrafficObserver{

    @Override
    public void show(TrafficLights ctx){

        System.out.println("-----"+ctx.getDirection()+" is currently in "+ctx.getLight().toString()+" state---------");
    }

}
