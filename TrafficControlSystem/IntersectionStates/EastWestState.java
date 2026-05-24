package TrafficControlSystem.IntersectionStates;

import TrafficControlSystem.Direction;
import TrafficControlSystem.IntersectionController;
import TrafficControlSystem.Light;
import TrafficControlSystem.LightStates.State;

public class EastWestState implements IntersectionState {

    @Override
    public void handle(IntersectionController ctx) throws InterruptedException{

       System.out.println("----------Starting the cycle of East-West State for----" +ctx.getIntersectionId()+"----");
        ctx.getLight(Direction.EAST).startGreen();
        ctx.getLight(Direction.WEST).startGreen();
        ctx.getLight(Direction.NORTH).setColor(Light.RED);
        ctx.getLight(Direction.SOUTH).setColor(Light.RED);

        Thread.sleep(ctx.getGreenDuration());

        ctx.getLight(Direction.EAST).transition();
        ctx.getLight(Direction.WEST).transition();

        Thread.sleep(ctx.getYellowDuration());

        ctx.getLight(Direction.EAST).transition();
        ctx.getLight(Direction.WEST).transition();

        ctx.setState(new NorthSouthState());
        
    }
}
