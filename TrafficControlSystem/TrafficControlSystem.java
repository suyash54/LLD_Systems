package TrafficControlSystem;

import TrafficControlSystem.TrafficObserver.CentralMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrafficControlSystem {

    private static TrafficControlSystem instance;
    private final List<IntersectionController> intersections;
    private ExecutorService service;

    private TrafficControlSystem() {
        this.intersections = new ArrayList<>();
    }



    public static synchronized TrafficControlSystem getInstance(){
        if(instance == null){
            synchronized(TrafficControlSystem.class){
                instance = new TrafficControlSystem();
            }
        }
        return instance;
    }

    public void addIntersection(int intersectionId, int greenDuration, int yellowDuration) {
        IntersectionController intersection = new IntersectionController.Builder(intersectionId)
                .withDurations(greenDuration, yellowDuration)
                .addObserver(new CentralMonitor())
                .build();
        intersections.add(intersection);
    }

    public void startSystems(){
        if (intersections.isEmpty()) {
            System.out.println("No intersections to manage. System not starting.");
            return;
        }

        System.out.println("--- Starting Traffic Control System ---");
        service = Executors.newFixedThreadPool(intersections.size());
        intersections.forEach(service::submit);
    }

    public void stopSystem() {
        System.out.println("\n--- Shutting Down Traffic Control System ---");
        intersections.forEach(Thread::interrupt);
        service.shutdown();
        try {
            if (!service.awaitTermination(5, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
        }
        System.out.println("All intersections stopped. System shut down.");
    }
}
