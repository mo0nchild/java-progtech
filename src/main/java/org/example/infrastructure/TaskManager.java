package org.example.infrastructure;

import org.example.models.ITask;
import org.example.services.implement.Consumer;
import org.example.services.implement.Producer;
import org.example.services.implement.TaskQueue;
import org.example.services.interfaces.ITaskQueue;

import java.util.ArrayList;
import java.util.List;

public class TaskManager<TaskType extends ITask> {
    private final List<Producer<TaskType>> producers = new ArrayList<>();
    private final List<Consumer<TaskType>> consumers = new ArrayList<>();
    private final ITaskQueue<TaskType> taskQueue;
    public interface IConsumerFactory<TaskType extends ITask> {
        public Consumer<TaskType> create(ITaskQueue<TaskType> tasksQueue, String name, Object mutex);
    }
    public interface IProducerFactory<TaskType extends ITask> {
        public Producer<TaskType> create(String name, ITaskQueue<TaskType> tasksQueue, Object mutex);
    }
    public TaskManager(int bufferSize) {
        super();
        this.taskQueue = new TaskQueue<>(bufferSize);
    }
    public void waitIsAllDone() {
        while (true) {
            var producersCheck = true;
            for (var item : this.producers) {
                if (item.getTaskCount() > 0) producersCheck = false;
            }
            if(producersCheck && this.taskQueue.getTaskCount() == 0) break;
        }
        for (var item : this.consumers) item.dispose();
        for (var item : this.producers) item.dispose();
    }
    public void publishTask(TaskType task) {
        if (this.producers.isEmpty()) return;
        var producerMin = this.producers.stream().min(Producer::compareTo);
        producerMin.orElse(this.producers.get(0)).addTask(task);
    }
    public final void addConsumer(String name, IConsumerFactory<TaskType> factory) {
        this.consumers.add(factory.create(this.taskQueue, name, this));
    }
    public final void addProducer(String name, IProducerFactory<TaskType> factory) {
        this.producers.add(factory.create(name, this.taskQueue, this));
    }
}
