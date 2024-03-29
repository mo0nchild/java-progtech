package org.example.models;

import java.io.Serializable;

public class Train implements Serializable, ITask {
    private final String name;
    private final Integer id, carriageCount;
    public Train(int id, String name, int size) {
        super();
        this.name = name;
        this.id = id;
        this.carriageCount = size;
    }
    public final Integer getId() { return this.id; }
    public final String getName() { return this.name; }
    public final Integer getCarriageCount() { return this.carriageCount; }
    @Override
    public String toString() {
        return String.format("id: %s, name: %s, carriages: %s",
                this.id, this.name, this.carriageCount);
    }
    @Override
    public void handleTask() { System.out.println(this); }
}
