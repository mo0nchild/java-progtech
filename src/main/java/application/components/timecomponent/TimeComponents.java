package application.components.timecomponent;

import application.components.imagecomponent.services.implement.CoolImageDirector;
import application.services.interfaces.IObserver;
import application.services.interfaces.NotifyArgs;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class TimeComponents implements IObserver {
    private final BorderPane componentBody = new BorderPane();
    private final Label timeLabel = new Label();
    public TimeComponents() {
        super();
        this.timeLabel.setStyle("-fx-font-size: 20px;");
    }
    public final BorderPane getComponent() { return this.componentBody; }
    @Override
    public void update(NotifyArgs args) {
        var dateFormat = new SimpleDateFormat("HH:mm:ss");
        var currentTime = dateFormat.format(new Date());
        Platform.runLater(() -> {
            this.timeLabel.setText(String.format("Текущее время: %s", currentTime));
            this.componentBody.setCenter(this.timeLabel);
        });
    }
}
