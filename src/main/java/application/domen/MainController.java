package application.domen;

import application.components.imagecomponent.ImageComponent;
import application.components.spinnercomponent.SpinnerComponent;
import application.components.timecomponent.TimeComponents;
import application.services.implement.TimeServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public final class MainController implements Initializable {
    public static final Integer RefreshDefaultDelay = 2000;
    private TimeServer timeServer = new TimeServer(RefreshDefaultDelay);
    @FXML
    public BorderPane imageContent, spinnerContent, timeContent;
    @FXML
    public Slider speedSlider;
    private final TimeComponents timeComponent = new TimeComponents();
    private final ImageComponent imageComponent = new ImageComponent();
    private final SpinnerComponent spinnerComponent = new SpinnerComponent();

    private Integer currentSpeed = RefreshDefaultDelay;
    public void refreshButtonClickedHandler(ActionEvent actionEvent) {
        this.currentSpeed = (RefreshDefaultDelay / (int)this.speedSlider.getValue());
        var subscribers = this.timeServer.getSubscribers();

        this.timeServer.detach(timeComponent);
        this.timeServer.detach(imageComponent);
        this.timeServer.detach(spinnerComponent);
        this.timeServer = new TimeServer(this.currentSpeed, subscribers);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.imageContent.setCenter(imageComponent.getComponent());
        this.timeContent.setCenter(timeComponent.getComponent());
        this.spinnerContent.setCenter(spinnerComponent.getComponent());

        this.timeServer.attach(timeComponent);
        this.timeServer.attach(imageComponent);
        this.timeServer.attach(spinnerComponent);
    }
}