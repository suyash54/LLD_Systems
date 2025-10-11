package ScheduledExecutorService;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingDeque;

public class SchedulerThread extends Thread{
  private final PriorityQueue<ScheduledTask> scheduledTasks;
  private final BlockingDeque<ScheduledTask> executionQueue;
  private final Object lock;
  private volatile boolean isRunning;

  public SchedulerThread(PriorityQueue<ScheduledTask> scheduledTasks,BlockingDeque<ScheduledTask> executionQueue,Object lock){
      this.scheduledTasks = scheduledTasks;
      this.executionQueue = executionQueue;
      this.lock = lock;
      this.isRunning = true;
  }

  public void run(){
      while(isRunning){
          synchronized (lock) {
              try {
                  while (scheduledTasks.isEmpty() && isRunning) {
                      lock.wait();
                  }

                  if (!isRunning) break;

                  ScheduledTask task = scheduledTasks.peek();
                  if (task == null)
                      continue;

                  long currentTime = System.currentTimeMillis();
                  long timeToWait = task.getNextExecutionTime() - currentTime;

                  if (timeToWait <= 0) {

                      scheduledTasks.poll();

                      if (!task.isCancelled()) {
                          if (task.getTaskType() == ScheduledTask.TaskType.FIXED_RATE) {
                              long nextExecutionTime = task.getNextExecutionTime() + task.getPeriod();
                              ScheduledTask nextTask = new ScheduledTask(
                                      task.getTask(),
                                      nextExecutionTime,
                                      task.getPeriod(),
                                      ScheduledTask.TaskType.FIXED_RATE
                              );
                              scheduledTasks.offer(nextTask);

                              executionQueue.offer(task);
                          }

                          if (task.getTaskType() == ScheduledTask.TaskType.FIXED_DELAY) {
                              Runnable originalTask = task.getTask();
                              ScheduledTask finalTask = task;
                              Runnable wrappedTask = () -> {
                                  try {
                                      originalTask.run();
                                  } finally {
                                      long nextExecutionTime = System.currentTimeMillis() + task.getPeriod();
                                      ScheduledTask nextTask = new ScheduledTask(
                                              originalTask,
                                              nextExecutionTime,
                                              finalTask.getPeriod(),
                                              finalTask.getTaskType()
                                      );

                                      synchronized (lock) {
                                          scheduledTasks.add(nextTask);
                                          lock.notify();
                                      }
                                  }
                              };

                              ScheduledTask wrappedScheduledTask = new ScheduledTask(
                                      wrappedTask,
                                      0,
                                      0,
                                      ScheduledTask.TaskType.ONE_TIME
                              );
                              executionQueue.offer(wrappedScheduledTask);
                          } else {
                              executionQueue.offer(task);
                          }
                      }
                  }
                  else{
                      lock.wait(timeToWait);
                  }
              }catch(Exception ex){
                  System.err.println("Error occurred"+ex.getMessage());
                  Thread.currentThread().interrupt();
                  break;
              }
          }
      }
  }

    public void shutdown() {
        synchronized (lock) {
            this.isRunning = false;
            lock.notifyAll();
        }
    }
}
