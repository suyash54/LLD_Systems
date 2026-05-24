package TrainPlatformSystem;

public class Train {
    int id;
    int arrivalTime;
    int waitTime;

    public Train(int id,int arrivalTime,int waitTime){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.waitTime = waitTime;
    }

    public int getTrainId(){
        return this.id;
    }

    public int getTrainArrivalTime(){
        return this.arrivalTime;
    }

    public int getTrainWaitTime(){
        return this.waitTime;
    }
}
