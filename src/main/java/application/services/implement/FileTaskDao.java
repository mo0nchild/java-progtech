package application.services.implement;

import application.models.Task;
import application.services.interfaces.ITaskDao;

import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FileTaskDao implements ITaskDao {
    private final ObjectMapper mapper = new ObjectMapper(){{
        writerWithDefaultPrettyPrinter();
    }};
    private final TypeReference<List<Task>> dataType = new TypeReference<>() { };
    private final File dataFile = new File("tasks.json");
    public FileTaskDao() {
        super();
        if(!this.dataFile.exists()) {
            try {
                var created = this.dataFile.createNewFile();
                this.mapper.writeValue(this.dataFile, new ArrayList<>());
            }
            catch (IOException error) { throw new RuntimeException(error); }
        }
    }
    @Override
    public Future<List<Task>> getAllTasks() {
        return CompletableFuture.supplyAsync(() -> {
            try { return this.mapper.readValue(this.dataFile,  this.dataType); }
            catch (IOException error) { throw new RuntimeException(error); }
        });
    }
    @Override
    public Future<Optional<Task>> getTaskById(int id) {
        return CompletableFuture.supplyAsync(() -> {
            List<Task> tasksList;
            try {
                tasksList = this.mapper.readValue(this.dataFile,  this.dataType);
            }
            catch (IOException error) { throw new RuntimeException(error); }
            return tasksList.stream().filter(item -> item.getId() == id).findFirst();
        });
    }
    @Override
    public Future<Void> addTask(Task task) {
        return CompletableFuture.runAsync(() -> {
            try {
                var tasksList = this.getAllTasks().get();
                for(var item : tasksList) {
                    if(item.getId() == task.getId()) return;
                }
                tasksList.add(task);
                this.mapper.writeValue(this.dataFile, tasksList);
            }
            catch (Exception error) { throw new RuntimeException(error); }
        });
    }
    @Override
    public Future<Void> updateTask(Task task) {
        return CompletableFuture.runAsync(() -> {
            try {
                var tasksList = this.getAllTasks().get();
                for(var index = 0; index < tasksList.size(); index++) {
                    if(tasksList.get(index).getId() == task.getId()) continue;
                    tasksList.set(index, task);
                    break;
                }
                this.mapper.writeValue(this.dataFile, tasksList);
            }
            catch (Exception error) { throw new RuntimeException(error); }
        });
    }
    @Override
    public Future<Void> deleteTask(int id) {
        return CompletableFuture.runAsync(() -> {
            try {
                var tasksList = this.getAllTasks().get();
                for (var index = 0; index < tasksList.size(); index++) {
                    if(tasksList.get(index).getId() == id) {
                        tasksList.remove(index);
                        break;
                    }
                }
                this.mapper.writeValue(this.dataFile, tasksList);
            }
            catch (Exception error) { throw new RuntimeException(error); }
        });
    }
}
