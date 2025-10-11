package ScheduledExecutorService;

public class ScheduledTask implements Comparable<ScheduledTask> {
    enum TaskType{
        ONE_TIME,
        FIXED_RATE,
        FIXED_DELAY
    }
    private final Runnable task;
    private long nextExecutionTime;
    private final long period;
    private final TaskType taskType;
    private volatile boolean cancelled;

    public ScheduledTask(Runnable task, long nextExecutionTime, long period, TaskType taskType){
        this.task = task;
        this.nextExecutionTime = nextExecutionTime;
        this.period = period;
        this.taskType = taskType;
        this.cancelled = false;
    }

    public Runnable getTask(){
        return task;
    }
    public Long getNextExecutionTime(){
        return nextExecutionTime;
    }
    public Long getPeriod(){
        return period;
    }
    public TaskType getTaskType(){
        return taskType;
    }
    public boolean isCancelled(){
        return this.cancelled;
    }
    public boolean isPeriodic(){
        return this.taskType == TaskType.FIXED_DELAY || this.taskType == TaskType.FIXED_RATE;
    }
    public int compareTo(ScheduledTask other){
        return Long.compare(this.nextExecutionTime,other.nextExecutionTime);
    }
}
