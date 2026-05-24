package TrainPlatformSystem;

public class Platform {

    int id;
    boolean isOccupied;
    int occupiedTill;

    public Platform(int id){
        this.id =id;
        this.isOccupied = false;
        this.occupiedTill = 0;
    }

    public int getPlatformId(){
        return this.id;
    }

    public boolean isOccupied(){
        return this.isOccupied;
    }

    public void setIsOccupied(boolean flag){
        this.isOccupied = flag;
    }

    public int getOccupiedTill(){
        return this.occupiedTill;
    }

    public void setOccupiedTill(int time){
        this.occupiedTill = time;
    }

}
