package org.example;

import org.example.infrastructure.TaskManager;
import org.example.models.Train;
import org.example.services.implement.Consumer;
import org.example.services.implement.Producer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Train> trainList = new ArrayList<>() {{
        add(new Train(1, "Train1", 3));
        add(new Train(2, "Train2", 10));
        add(new Train(3, "Train3", 5));
        add(new Train(4, "Train4", 20));
        add(new Train(5, "Train5", 100));
        add(new Train(6, "Train6", 75));
        add(new Train(7, "Train7", 36));
    }};

    public static void main(String[] args) {
        var taskManager = new TaskManager<Train>(3) {{
            addConsumer("Consumer1", Consumer::new);
            addConsumer("Consumer2", Consumer::new);
            addConsumer("Consumer3", Consumer::new);

            addProducer("Producer1", Producer::new);
            addProducer("Producer2", Producer::new);
            addProducer("Producer3", Producer::new);
        }};
        for (var item : trainList) taskManager.publishTask(item);
        taskManager.waitIsAllDone();
    }
}