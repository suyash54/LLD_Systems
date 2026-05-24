package ScheduledExecutorService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class WorkerThread extends Thread{

    private final BlockingQueue<ScheduledTask> taskQueue;
    private volatile boolean running;

    public WorkerThread(BlockingQueue<ScheduledTask> taskQueue){
        this.taskQueue = taskQueue;
        this.running = true;
    }

    @Override
    public void run(){

        while(running){
            try{
                ScheduledTask task = taskQueue.poll(100, TimeUnit.MILLISECONDS);
                if(task!=null && !task.isCancelled()){
                    try{
                        task.getTask().run();
                    } catch(Exception ex){
                        System.err.println("Error occured in executing task"+ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void shutdown(){
        this.running = false;
        this.interrupt();
    }
}
