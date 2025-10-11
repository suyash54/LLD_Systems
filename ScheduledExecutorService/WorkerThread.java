package ScheduledExecutorService;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class WorkerThread extends Thread{
  private final BlockingDeque<ScheduledTask> taskQueue;
  private volatile boolean running;

  public WorkerThread(BlockingDeque<ScheduledTask> taskQueue){
      this.taskQueue = taskQueue;
      this.running = true;
  }

  public void run(){
      while(running){
          try {
              ScheduledTask scheduledTask = taskQueue.poll(100, TimeUnit.MILLISECONDS);
              if(scheduledTask != null && !scheduledTask.isCancelled()){
                  try {
                      scheduledTask.getTask().run();
                  }catch(Exception ex){
                      System.err.println("Error occured in executing task"+ex.getMessage());
                      ex.printStackTrace();
                  }
              }
          }catch(InterruptedException ex){
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
