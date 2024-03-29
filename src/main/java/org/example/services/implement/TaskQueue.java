package org.example.services.implement;

import org.example.models.ITask;
import org.example.models.Train;
import org.example.services.interfaces.ITaskQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Queue;

public class TaskQueue<TaskType extends ITask> implements ITaskQueue<TaskType> {
    private final Queue<TaskType> tasks = new ArrayDeque<>();
    private final Integer bufferSize;
    public TaskQueue(int bufferSize) {
        super();
        this.bufferSize = bufferSize;
    }
    public final Integer getBufferSize() { return this.bufferSize; }
    @Override
    public Optional<TaskType> getTask() throws InterruptedException {
        synchronized (this) {
            var currentTask = this.tasks.poll();
            return currentTask == null ? Optional.empty() : Optional.of(currentTask);
        }
    }
    @Override
    public boolean setTask(TaskType task) throws InterruptedException {
        synchronized (this) {
            if (this.tasks.size() < this.bufferSize) return this.tasks.offer(task);
        }
        return false;
    }
    @Override
    public final Integer getTaskCount() { return this.tasks.size(); }
}
