package application.services.factories;

import application.services.implement.DatabaseTaskDao;
import application.services.implement.FileTaskDao;
import application.services.implement.MemoryTaskDao;
import application.services.interfaces.ITaskDao;

public class TaskDaoFactory {
    public static enum FactoryTypes { DATABASE, FILE, MEMORY }
    public TaskDaoFactory() { super(); }

    public static ITaskDao createTaskDao(FactoryTypes type) {
        return switch(type) {
            case FILE -> new FileTaskDao();
            case DATABASE -> new DatabaseTaskDao();
            case MEMORY -> new MemoryTaskDao();
        };
    }
}
