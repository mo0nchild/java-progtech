package application.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    public static final String DONE = "Готово", PROGRESS = "В процессе", REJECT = "Отменен";
    private final String name, status;
    private final Integer id, time;
    @JsonCreator
    public Task(@JsonProperty("id") Integer id,
                @JsonProperty("name") String name,
                @JsonProperty("time") Integer time,
                @JsonProperty("status") String status) {
        this.name = name;
        this.time = time;
        this.id = id;
        this.status = status;
    }
    @JsonGetter
    public Integer getTime() { return this.time; }
    @JsonGetter
    public int getId() { return this.id; }
    @JsonGetter
    public String getStatus() { return this.status; }
    @JsonGetter
    public String getName() { return this.name; }
}
