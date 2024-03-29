package org.example.services.interfaces;

import java.util.Optional;

public interface ITaskQueue<TaskType> {
    public Optional<TaskType> getTask() throws InterruptedException;
    public boolean setTask(TaskType task) throws InterruptedException;
    public Integer getTaskCount();
}
