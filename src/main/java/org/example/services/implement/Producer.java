package org.example.services.implement;

import org.example.models.ITask;
import org.example.services.interfaces.ITaskQueue;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Producer<TaskType extends ITask> implements Runnable, Comparable<Producer> {
    private final ITaskQueue<TaskType> tasksQueue;
    private final Queue<TaskType> ownQueue = new ArrayDeque<>();
    private final Object syncMutex;
    private final String producerName;
    private boolean disposed = false;
    public Producer(String name, ITaskQueue<TaskType> tasksQueue, Object mutex) {
        super();
        this.tasksQueue = tasksQueue;
        this.syncMutex = mutex;
        this.producerName = name;
        new Thread(this).start();
    }
    public final Integer getTaskCount() { return this.ownQueue.size(); }
    public final void addTask(TaskType task) { this.ownQueue.offer(task); }
    public void dispose() { this.disposed = true; }
    @Override
    public void run() { while(!disposed) this.producerHandler(); }
    protected void producerHandler() {
        var currentTask = this.ownQueue.peek();
        if (currentTask == null) return;
        try {
            synchronized (this.syncMutex) {
                System.out.printf("Producer [%s] current tasks: %s\n", this.producerName, this.ownQueue.size());
            }
            Thread.sleep(1000);
            if (!this.tasksQueue.setTask(currentTask)) return;
            this.ownQueue.remove();
        }
        catch (InterruptedException error) { System.out.println(error.getMessage()); }
        catch (NoSuchElementException ignored) { }
    }
    @Override
    public int compareTo(Producer other) {
        return this.getTaskCount().compareTo(other.getTaskCount());
    }
}
