package application.services.implement;

import application.models.Task;
import application.services.interfaces.ITaskDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;

public class MemoryTaskDao implements ITaskDao {
    private final List<Task> memoryCache = new ArrayList<>();
    public MemoryTaskDao() { super(); }
    @Override
    public Future<List<Task>> getAllTasks() {
        return CompletableFuture.completedFuture(this.memoryCache);
    }
    @Override
    public Future<Optional<Task>> getTaskById(int id) {
        var futureTask = new FutureTask<Optional<Task>>(() -> {
            return this.memoryCache.stream()
                    .filter(item -> item.getId() == id)
                    .findFirst();
        });
        new Thread(futureTask).start();
        return futureTask;
    }
    @Override
    public Future<Void> addTask(Task task) {
        return CompletableFuture.runAsync(() -> {
            for(var item : this.memoryCache) {
                if(item.getId() == task.getId()) return;
            }
            this.memoryCache.add(task);
        });
    }
    @Override
    public Future<Void> updateTask(Task task) {
        return CompletableFuture.runAsync(() -> {
            for(var index = 0; index < this.memoryCache.size(); index++) {
                if(this.memoryCache.get(index).getId() == task.getId()) continue;
                this.memoryCache.set(index, task);
                return;
            }
        });
    }
    @Override
    public Future<Void> deleteTask(int id) {
        return CompletableFuture.runAsync(() -> {
            for (var index = 0; index < this.memoryCache.size(); index++) {
                if(this.memoryCache.get(index).getId() == id) {
                    this.memoryCache.remove(index);
                    return;
                }
            }
        });
    }
}
