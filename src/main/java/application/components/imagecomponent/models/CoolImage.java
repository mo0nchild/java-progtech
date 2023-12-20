package application.components.builder.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CoolImage {
    private final StackPane panel = new StackPane();
    private final Double width, height;
    public CoolImage(double width, double height) {
        super();
        this.height = height;
        this.width = width;
    }
    public final StackPane getPanel() { return this.panel; }
    public final Double getWidth() { return this.width; }
    public final Double getHeight() { return this.height; }
    public void addImage(Image image){
        var currentView = new ImageView();
        currentView.setImage(image);
        currentView.setFitHeight(this.height);
        currentView.setFitWidth(this.width);
        this.panel.getChildren().add(currentView);
    }
    public void addText(Text text){
        this.panel.getChildren().add(text);
    }
    public void display (Pane pane){
        pane.getChildren().add(panel);
    }
}
