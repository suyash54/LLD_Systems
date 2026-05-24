package TrafficControlSystem.IntersectionStates;

import TrafficControlSystem.Direction;
import TrafficControlSystem.IntersectionController;
import TrafficControlSystem.Light;

public class NorthSouthState implements IntersectionState{

    @Override
    public void handle(IntersectionController ctx) throws InterruptedException{

        System.out.println("----------Starting the cycle of North-South State for----" +ctx.getIntersectionId()+"----");
        ctx.getLight(Direction.NORTH).startGreen();
        ctx.getLight(Direction.SOUTH).startGreen();
        ctx.getLight(Direction.EAST).setColor(Light.RED);
        ctx.getLight(Direction.WEST).setColor(Light.RED);

        Thread.sleep(ctx.getGreenDuration());

        ctx.getLight(Direction.NORTH).transition();
        ctx.getLight(Direction.SOUTH).transition();

        Thread.sleep(ctx.getYellowDuration());

        ctx.getLight(Direction.NORTH).transition();
        ctx.getLight(Direction.SOUTH).transition();

        ctx.setState(new EastWestState());

    }
}