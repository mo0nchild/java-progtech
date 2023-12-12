package application.domen;

import application.models.CoolImage;
import application.models.ImageAggregate;
import application.services.implement.CoolImageBuilder;
import application.services.implement.CoolImageDirector;
import application.services.interfaces.IBuilder;
import application.services.interfaces.Iterator;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public final class MainController implements Initializable {
    private final String filesPath = new File("src//main//resources//coolimages//").getAbsolutePath() + "\\";
    private final List<String> wordsList = List.of(
            "Время летит быстро,\nно воспоминания остаются",
            "Жизнь прекрасна,\nесли умеешь\nнаходить красоту ",
            "Будьте добрыми\nи щедрыми",
            "Победа находится\nза пределами\nзоны комфорта",
            "Верьте в себя и\nсвои способности"
    );
    private final List<Color> colorsList = List.of(Color.BLUE, Color.LIME, Color.ORANGE, Color.CRIMSON, Color.WHITE);
    private final Iterator<Image> iterator = new ImageAggregate(this.filesPath).createIterator();
    private CoolImage coolImage;
    private CoolImageDirector director;
    private IBuilder<CoolImage> builder;

    private final FadeTransition fade = new FadeTransition();
    private final Timeline timeline = new Timeline(new KeyFrame(new Duration(2000),
            actionEvent -> { nextButtonClickHandler(); setFadeStackOptions(); }));
    @FXML
    private BorderPane borderPane;
    @FXML
    ImageView imagePhoto;

    @FXML
    public final void backButtonClickHandler() {
        this.builder = new CoolImageBuilder(this.wordsList, this.colorsList);
        this.director = new CoolImageDirector(iterator.preview());

        this.borderPane.setCenter((this.coolImage = this.director.construct(this.builder)).getPanel());
    }
    @FXML
    public final void nextButtonClickHandler() {
        this.builder = new CoolImageBuilder(this.wordsList, this.colorsList);
        this.director = new CoolImageDirector(iterator.next());

        this.borderPane.setCenter((this.coolImage = this.director.construct(this.builder)).getPanel());
    }
    @FXML
    public final void beginLoopButtonClickHandler() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        setFadeStackOptions();
    }
    @FXML
    private void setFadeStackOptions() {
        fade.setNode(this.coolImage.getPanel());
        fade.setCycleCount(1); fade.setDuration(new Duration(1000));
        fade.setFromValue(0.0); fade.setToValue(1.0); fade.play();
    }
    public void stopLoopButtonClickHandler(ActionEvent actionEvent) {
        timeline.stop(); fade.stop();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        this.nextButtonClickHandler();
    }
}
