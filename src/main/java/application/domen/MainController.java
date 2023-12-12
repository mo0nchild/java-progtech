package application.domen;

import application.models.Circle;
import application.models.Shape;
import application.models.Square;
import application.models.Triangle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public final class MainController implements Initializable {
    @FXML
    public Canvas graphicContent;
    @FXML
    public ListView listView;
    public ObservableList<Shape> items;
    @FXML
    public final void drawShapeOnCanvas(MouseEvent mouseEvent) {
        var shape = (Shape)items.get(listView.getSelectionModel().getSelectedIndex()).clone();

        shape.setPosition((int)mouseEvent.getX(), (int)mouseEvent.getY());
        shape.shapeDraw(this.graphicContent.getGraphicsContext2D());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.items = FXCollections.observableArrayList(new Square(), new Circle(), new Triangle());
        this.listView.setItems(items);
        this.listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}