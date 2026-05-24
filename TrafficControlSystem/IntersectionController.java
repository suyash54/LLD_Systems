package TrafficControlSystem;

import TrafficControlSystem.IntersectionStates.IntersectionState;
import TrafficControlSystem.IntersectionStates.NorthSouthState;
import TrafficControlSystem.TrafficObserver.TrafficObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionController extends Thread {
    int intersectionId;
    Map<Direction,TrafficLights> trafficLights;
    IntersectionState currentState;
    List<TrafficObserver> observerList;
    int greenDuration;
    int yellowDuration;
    boolean isRunning;


    public IntersectionController(int intersectionId,int greenDuration,int yellowDuration,Map<Direction, TrafficLights> trafficLights){
        this.intersectionId = intersectionId;
        this.trafficLights = trafficLights;
        this.currentState = new NorthSouthState();
        this.observerList = new ArrayList<>();
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
        this.isRunning = true;
    }

    public int getIntersectionId() { return intersectionId; }
    public int getGreenDuration() { return greenDuration; }
    public int getYellowDuration() { return yellowDuration; }
    public void setGreenDuration(int greenDuration) { this.greenDuration = greenDuration;}
    public void setYellowDuration(int yellowDuration){ this.yellowDuration = yellowDuration;}

    public TrafficLights getLight(Direction direction) { return trafficLights.get(direction); }


    public void setState(IntersectionState state){
        this.currentState = state;
    }

    public IntersectionState getIntersectionState(){
        return this.currentState;
    }

    public void addObserver(TrafficObserver observer){
        this.observerList.add(observer);
    }

    public void removeObserver(TrafficObserver observer){
        this.observerList.remove(observer);
    }

    public void notifyObserver(){
        for(TrafficObserver observer: observerList){
            List<TrafficLights> trafficLights = this.trafficLights.values().stream().toList();
            trafficLights.forEach(observer::show);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                this.currentState.handle(this);
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
                System.out.println("-----Intersection---"+intersectionId+"---got interrupted due to----"+ex.getMessage());
                isRunning = false;
            }
        }
    }

    //------------Builder------

    public static class Builder{
        private final int intersectionId;
        private int greenDuration = 5000;
        private int yellowDuration = 1000;
        private List<TrafficObserver> observerList = new ArrayList<>();

        public Builder(int id){
            this.intersectionId = id;
        }

        public Builder withDurations(int greenDuration,int yellowDuration){
            this.greenDuration = greenDuration;
            this.yellowDuration = yellowDuration;
            return this;
        }

        public Builder addObserver(TrafficObserver observer){
            this.observerList.add(observer);
            return this;
        }

        public IntersectionController build(){
            Map<Direction,TrafficLights> lights = new HashMap<>();
            for(Direction dir: Direction.values()){
                TrafficLights light = new TrafficLights(dir,intersectionId);
                lights.put(dir,light);
            }
            return new IntersectionController(intersectionId,greenDuration,yellowDuration,lights);
        }



    }

}
