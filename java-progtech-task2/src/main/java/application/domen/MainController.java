package application.domen;

import application.models.shape.collections.IShapeStackCollection;
import application.models.shape.collections.ShapeStackCollection;
import application.models.shape.commons.IShapeFactoryFromName;
import application.models.shape.commons.ShapeFactoryException;
import application.models.shape.commons.ShapeFactoryFromName;
import application.models.shape.Circle;
import application.models.shape.Shape;
import application.models.shape.Square;
import application.models.shape.Triangle;
import application.services.repository.IRepository;
import application.services.repository.ShapeRepository;
import application.services.repository.commons.ShapeRepositoryFactory;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Canvas sheet;
    @FXML
    public ColorPicker background, border;
    @FXML
    public Spinner<Integer> sizeNumber;
    @FXML
    public Label infoShape;
    @FXML
    public Button clearButton;
    @FXML
    public TextField shapeName;
    @FXML
    public TabPane mainTabPane;

    private static final String databaseFilename = "database.txt";
    private IShapeFactoryFromName shapeFactory = new ShapeFactoryFromName();
    private IShapeStackCollection shapeCollection = new ShapeStackCollection(
            new ShapeRepositoryFactory(MainController.databaseFilename, this.shapeFactory));
    private Shape shape = null;

    @FXML
    public final void selectCircleHandler(ActionEvent actionEvent) {
        this.shape = new Circle(border.getValue(), background.getValue(), Point2D.ZERO, sizeNumber.getValue());
    }

    @FXML
    public void selectSquareHandler(ActionEvent actionEvent) {
        this.shape = new Square(border.getValue(), background.getValue(), Point2D.ZERO, sizeNumber.getValue());
    }

    @FXML
    public void selectTriangleHandler(ActionEvent actionEvent) {
        this.shape = new Triangle(border.getValue(), background.getValue(), Point2D.ZERO, sizeNumber.getValue());
    }

    @FXML
    public final void drawShapeHandler(MouseEvent mouseEvent) {
        if (this.mainTabPane.getSelectionModel().isSelected(1)) {
            try { this.shape = this.shapeFactory.createShape(this.shapeName.getText()); }
            catch (ShapeFactoryException error) {
                new Alert(Alert.AlertType.ERROR, error.getMessage()).showAndWait();
            }
        }
        if(this.shape == null) return;
        this.shape.setPosition(mouseEvent.getX(), mouseEvent.getY());

        try { this.shapeCollection.putItem(shape); }
        catch (Exception error) {
            new Alert(Alert.AlertType.ERROR, error.getMessage()).showAndWait();
        }
        this.infoShape.setText(this.shape.toString());
    }
    @FXML
    public void clearCanvasHandler(ActionEvent actionEvent) {
        try { this.shapeCollection.removeAll(); }
        catch (Exception error) {
            new Alert(Alert.AlertType.ERROR, error.getMessage()).showAndWait();
        }
        this.infoShape.setText(String.format("Определение позиции фигуры: (0, 0)"));
    }
    @FXML
    public void removeShapeHandler(ActionEvent actionEvent) {
        try { this.shapeCollection.removeItem(); }
        catch (Exception error) {
            new Alert(Alert.AlertType.ERROR, error.getMessage()).showAndWait();
        }
        this.infoShape.setText(String.format("Возвращение назад"));
    }
    private void renderCanvasState() {
        this.sheet.getGraphicsContext2D().clearRect(0, 0, this.sheet.getWidth(), this.sheet.getHeight());
        try {
            for (var item : this.shapeCollection.getAll()) item.shapeDraw(this.sheet.getGraphicsContext2D());
        }
        catch (Exception error) { throw new RuntimeException(error); }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (InputStream imageStream = Objects.requireNonNull(this.getClass().getResourceAsStream("trash.png"))) {
            final ImageView buttonIcon = new ImageView(new Image(imageStream));

            buttonIcon.setFitHeight(20);
            buttonIcon.setFitWidth(20);
            this.clearButton.setGraphic(buttonIcon);
        }
        catch (Exception error) {
            new Alert(Alert.AlertType.ERROR, error.getMessage()).showAndWait();
        }
        this.border.setValue(new Color(0.84, 0.54, 0.86, 1.0));
        this.background.setValue(new Color(0.95, 0.8, 0.93, 1.0));

        this.shapeCollection.addListener((Observable sender) -> { this.renderCanvasState(); });
        this.renderCanvasState();
    }
}