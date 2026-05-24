package ScheduledExecutorService;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorService {

    private final BlockingQueue<ScheduledTask> executionTasks;
    private final PriorityQueue<ScheduledTask> scheduledTasks;
    private final WorkerThread[] workerThreads;
    private final SchedularThread schedularThread;
    private volatile boolean shutdown;

    public ScheduledExecutorService(int poolSize){
        executionTasks = new LinkedBlockingQueue<>();
        scheduledTasks = new PriorityQueue<>();
        this.shutdown = false;

        this.workerThreads = new WorkerThread[poolSize];
        for(int i=0;i<poolSize;i++){
            workerThreads[i] = new WorkerThread(executionTasks);
            workerThreads[i].start();
        }

        this.schedularThread = new SchedularThread(scheduledTasks,executionTasks);
        this.schedularThread.start();
    }

    public void schedule(Runnable task, long delay, TimeUnit time){
        if(shutdown){
            throw new IllegalStateException("Executor has been shutdown");
        }

        long delayInMilliSec = time.toMillis(delay);
        long executionTime = System.currentTimeMillis() + delayInMilliSec;

        ScheduledTask scheduledTask = new ScheduledTask(
                task,
                executionTime,
                0,
                ScheduledTask.TaskType.ONE_TIME
        );
        schedularThread.addTask(scheduledTask);
    }


    public void scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        if (shutdown) {
            throw new IllegalStateException("Executor has been shut down");
        }

        long initialDelayMillis = unit.toMillis(initialDelay);
        long periodMillis = unit.toMillis(period);
        long executionTime = System.currentTimeMillis() + initialDelayMillis;

        ScheduledTask scheduledTask = new ScheduledTask(
                task,
                executionTime,
                periodMillis,
                ScheduledTask.TaskType.FIXED_RATE
        );

        schedularThread.addTask(scheduledTask);
    }

    public void scheduleWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit) {
        if (shutdown) {
            throw new IllegalStateException("Executor has been shut down");
        }

        long initialDelayMillis = unit.toMillis(initialDelay);
        long delayMillis = unit.toMillis(delay);
        long executionTime = System.currentTimeMillis() + initialDelayMillis;

        ScheduledTask scheduledTask = new ScheduledTask(
                task,
                executionTime,
                delayMillis,
                ScheduledTask.TaskType.FIXED_DELAY
        );

        schedularThread.addTask(scheduledTask);
    }

    public void shutdown() {
        if (shutdown) {
            return;
        }

        shutdown = true;

        // Shutdown scheduler thread
        schedularThread.shutdown();

        // Shutdown worker threads
        for (WorkerThread worker : workerThreads) {
            worker.shutdown();
        }
    }

    public boolean awaitTermination(long timeout,TimeUnit unit) throws InterruptedException {
        long timeOutInMillis = unit.toMillis(timeout);
        long startTime = System.currentTimeMillis();

        schedularThread.join(timeOutInMillis);

        long elasped = System.currentTimeMillis() - startTime;
        long remaining = timeOutInMillis - elasped;

        for(WorkerThread workerThread: workerThreads){
            if(remaining <= 0){
                return false;
            }
            workerThread.join(remaining);
            elasped = System.currentTimeMillis() - startTime;
            remaining = timeOutInMillis - elasped;
        }
        return true;
    }

    public static void main(String[] args)  throws InterruptedException{
        ScheduledExecutorService executor = new ScheduledExecutorService(3);

        System.out.println("Starting scheduled executor service...\n");

        // Test 1: One-time scheduled task
        System.out.println("Test 1: Scheduling one-time task with 2 second delay");
        executor.schedule(() -> {
            System.out.println("[" + System.currentTimeMillis() + "] One-time task executed");
        }, 2, TimeUnit.SECONDS);

        // Test 2: Fixed rate task
        System.out.println("Test 2: Scheduling fixed-rate task (initial: 1s, period: 3s)");
        executor.scheduleAtFixedRate(() -> {
            System.out.println("[" + System.currentTimeMillis() + "] Fixed-rate task executed");
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 3, TimeUnit.SECONDS);

        // Test 3: Fixed delay task
        System.out.println("Test 3: Scheduling fixed-delay task (initial: 1s, delay: 2s)");
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("[" + System.currentTimeMillis() + "] Fixed-delay task executed");
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 2, TimeUnit.SECONDS);

        // Let tasks run for 15 seconds
        Thread.sleep(15000);

        System.out.println("\nShutting down executor...");
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Executor shut down complete");
    }


}
