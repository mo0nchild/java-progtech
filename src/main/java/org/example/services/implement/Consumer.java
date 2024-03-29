package org.example.services.implement;

import org.example.models.ITask;
import org.example.services.interfaces.ITaskQueue;

public class Consumer<TaskType extends ITask> implements Runnable {
    private final ITaskQueue<TaskType> tasksQueue;
    private final String consumerName;
    private final Object syncMutex;
    private boolean disposed = false;
    public Consumer(ITaskQueue<TaskType> tasksQueue, String name, Object mutex) {
        super();
        this.tasksQueue = tasksQueue;
        this.consumerName = name;
        this.syncMutex = mutex;
        new Thread(this).start();
    }
    public void dispose() { this.disposed = true; }
    @Override
    public void run() { while(!disposed) this.consumerHandler(); }
    protected void consumerHandler() {
        try {
            this.tasksQueue.getTask().ifPresent(task -> {
                synchronized (this.syncMutex) {
                    System.out.printf("Complete with [%s]: ", this.consumerName);
                    task.handleTask();
                }
            });
            Thread.sleep(1000);
        }
        catch (InterruptedException error) { System.out.println(error.getMessage()); }
    }
}
