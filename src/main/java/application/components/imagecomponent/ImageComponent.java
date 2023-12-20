package application.components.imagecomponent;

import application.components.imagecomponent.models.CoolImage;
import application.components.imagecomponent.models.ImageAggregate;
import application.components.imagecomponent.services.implement.CoolImageBuilder;
import application.components.imagecomponent.services.implement.CoolImageDirector;
import application.components.imagecomponent.services.implement.ImageIterator;
import application.components.imagecomponent.services.interfaces.IBuilder;
import application.components.imagecomponent.services.interfaces.Iterator;
import application.services.interfaces.IObserver;
import application.services.interfaces.NotifyArgs;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.util.List;

public class ImageComponent implements IObserver {
    protected final String filesPath = new File("src//main//resources//coolimages//").getAbsolutePath() + "\\";
    protected final List<String> wordsList = List.of(
            "Время летит быстро,\nно воспоминания остаются",
            "Жизнь прекрасна,\nесли умеешь\nнаходить красоту ",
            "Будьте добрыми\nи щедрыми",
            "Победа находится\nза пределами\nзоны комфорта",
            "Верьте в себя и\nсвои способности"
    );
    protected final List<Color> colorsList = List.of(Color.BLUE, Color.LIME, Color.ORANGE, Color.CRIMSON, Color.WHITE);
    private final Iterator<Image> iterator = new ImageAggregate(this.filesPath).createIterator();
    protected CoolImage coolImage;
    protected BorderPane componentBody = new BorderPane();
    public ImageComponent() {
        super();
        this.update(new NotifyArgs(1, null));
    }
    public final BorderPane getComponent() { return this.componentBody; }
    public final StackPane getImage() { return this.coolImage.getPanel(); }

    @Override
    public void update(NotifyArgs args) {
        var imageBuilder = new CoolImageBuilder(this.wordsList, this.colorsList);
        Platform.runLater(() -> {
            this.coolImage = new CoolImageDirector(iterator.next()).construct(imageBuilder);
            this.componentBody.setCenter(this.coolImage.getPanel());
        });
    }
}
