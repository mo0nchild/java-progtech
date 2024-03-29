package application.services.interfaces;

import application.models.Task;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface ITaskDao {
    public Future<List<Task>> getAllTasks();
    public Future<Optional<Task>> getTaskById(int id);

    public Future<Void> addTask(Task task);
    public Future<Void> updateTask(Task task);
    public Future<Void> deleteTask(int id);
}
