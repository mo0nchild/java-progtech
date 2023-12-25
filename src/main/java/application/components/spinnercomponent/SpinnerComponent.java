package application.components.spinnercomponent;

import application.services.interfaces.IObserver;
import application.services.interfaces.NotifyArgs;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SpinnerComponent implements IObserver {
    private final BorderPane componentBody = new BorderPane();
    private final Text rotatingText = new Text("Вращающийся текст!");
    private Integer angle = 5;
    public SpinnerComponent() {
        super();
        this.rotatingText.setFont(new Font(20));
    }
    public final BorderPane getComponent() { return this.componentBody; }
    public final Integer getAngle() { return this.angle; }
    public final void setAngle(Integer value) { this.angle = value; }
    @Override
    public void update() {
        var rotateTransition = new RotateTransition(Duration.seconds(0.1), rotatingText);
        rotateTransition.setByAngle(this.angle);
        Platform.runLater(() -> { \\ использовать Shape javafx чтобы не обновляться через платформу
            rotateTransition.play();
            this.componentBody.setCenter(rotatingText);
        });
    }
}
