package TrainPlatformSystem;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrainPlatformManager {

    private static volatile TrainPlatformManager instance;
    PriorityQueue<Platform> busy;

    PriorityQueue<Platform> free;

    Platform[] platforms;


    //Thread-Safe Singleton

    public static TrainPlatformManager getInstance(int platformCount) {
        if (instance == null) {
            synchronized (TrainPlatformManager.class) {
                if (instance == null) {
                    instance = new TrainPlatformManager(platformCount);
                }
            }
        }
        return instance;
    }

    public TrainPlatformManager(int platformCount){
         busy = new PriorityQueue<>(Comparator.comparingInt(Platform::getOccupiedTill)
                 .thenComparingInt(Platform::getPlatformId));

         free = new PriorityQueue<>(Comparator.comparingInt(Platform::getPlatformId));
         platforms = new Platform[platformCount];

         for(int i=0;i<platformCount;i++){
             Platform p = new Platform(i);
             platforms[i] = p;
             free.offer(p);
         }
    }

    String assignPlatform(int trainId,int arrivalTime,int waitTime){
       int platformNumber,delayTime;
        while(!busy.isEmpty()){
            Platform p = busy.peek();
            if(p.getOccupiedTill()<arrivalTime){
                p.setIsOccupied(false);
                p.setOccupiedTill(0);
                free.offer(p);
                busy.poll();
            }
            else{
                break;
            }
        }

        if(!free.isEmpty()){
            Platform p = free.poll();
            p.setOccupiedTill(arrivalTime+waitTime);
            p.setIsOccupied(true);
            busy.offer(p);
            platformNumber = p.getPlatformId();
            delayTime = 0;
        }
        else{
            Platform p = busy.peek();
            int getOccupiedTime = p.getOccupiedTill();
            delayTime = getOccupiedTime - arrivalTime + 1;
            platformNumber = p.getPlatformId();
            busy.poll();
            p.setOccupiedTill(getOccupiedTime+waitTime);
            busy.offer(p);
        }

        return (platformNumber + "," + delayTime);
    }


}
