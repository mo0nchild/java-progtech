package application.services.implement;

import application.models.Task;
import application.services.interfaces.ITaskDao;
import org.postgresql.Driver;
import org.postgresql.jdbc.PgConnection;
import org.postgresql.jdbc.PgResultSet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class DatabaseTaskDao implements ITaskDao {
    private final String url = "jdbc:postgresql://localhost:5432/javaprogtech";
    public DatabaseTaskDao() { super(); }
    public static interface ConnectionUsing {
        public void accept(Connection connection) throws SQLException;
    }
    public static class ThanksJavaForBestLambda<TValue> {
        private TValue value;
        public ThanksJavaForBestLambda(TValue value) {
            super();
            this.value = value;
        }
        public final TValue getValue() { return this.value; }
        public final void setValue(TValue value) { this.value = value; }
    }
    private void useConnection(ConnectionUsing manager) {
        try (var connection = DriverManager.getConnection(url, "postgres", "1234567890")){
            manager.accept(connection);
        }
        catch (SQLException error) { throw new RuntimeException(error); }
    }
    @Override
    public Future<List<Task>> getAllTasks() {
        return CompletableFuture.supplyAsync(() -> {
            var result = new ArrayList<Task>();
            this.useConnection(connection -> {
                var statement = connection.prepareStatement("SELECT * FROM tasks");
                var dbResult = statement.executeQuery();
                while (dbResult.next()) {
                    var task = new Task(
                            dbResult.getInt("id"),
                            dbResult.getString("name"),
                            dbResult.getInt("time"),
                            dbResult.getString("status")
                    );
                    result.add(task);
                }
            });
            return result;
        });
    }
    @Override
    public Future<Optional<Task>> getTaskById(int id) {
        return CompletableFuture.supplyAsync(() -> {
            var result = new ThanksJavaForBestLambda<Optional<Task>>(Optional.empty());
            this.useConnection(connection -> {
                var statement = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?");
                statement.setInt(0, id);
                var dbResult = statement.executeQuery();
                if (dbResult.next()) {
                    var record = Optional.of(new Task(
                            dbResult.getInt("id"),
                            dbResult.getString("name"),
                            dbResult.getInt("time"),
                            dbResult.getString("status")
                    ));
                    result.setValue(record);
                }
            });
            return result.getValue();
        });
    }
    @Override
    public Future<Void> addTask(Task task) {
        return CompletableFuture.runAsync(() -> {
            this.useConnection(connection -> {
                var statement = connection.prepareStatement(
                        "INSERT INTO tasks (name, time, status) VALUES (?, ?, ?)");
                statement.setString(1, task.getName());
                statement.setInt(2, task.getTime());
                statement.setString(3, task.getStatus());
                var rows = statement.executeUpdate();
                if(rows <= 0) throw new SQLException("Не удалось добавить запись");
            });
        });
    }
    @Override
    public Future<Void> updateTask(Task task) {
        return CompletableFuture.runAsync(() -> {
            this.useConnection(connection -> {
                var statement = connection.prepareStatement(
                        "UPDATE tasks SET name = ?, time = ?, status = ? WHERE id = ?");
                statement.setString(1, task.getName());
                statement.setInt(2, task.getTime());
                statement.setString(3, task.getStatus());
                statement.setInt(4, task.getId());
                var rows = statement.executeUpdate();
                if(rows <= 0) throw new SQLException("Не удалось обновить запись");
            });
        });
    }
    @Override
    public Future<Void> deleteTask(int id) {
        return CompletableFuture.runAsync(() -> {
            this.useConnection(connection -> {
                var statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
                statement.setInt(1, id);
                var rows = statement.executeUpdate();
                if(rows <= 0) throw new SQLException("Не удалось удалить запись");
            });
        });
    }
}
