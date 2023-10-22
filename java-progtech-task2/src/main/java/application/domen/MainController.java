package application.domen;

import application.commons.ShapeFactory;
import application.commons.ShapeFactoryException;
import application.commons.ShapeFactoryFromName;
import application.models.Circle;
import application.models.Shape;
import application.models.Square;
import application.models.Triangle;
import application.services.IRepository;
import application.services.RepositoryException;
import application.services.ShapeRepository;
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
import javafx.scene.paint.Paint;

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

    private ShapeFactory factory = new ShapeFactoryFromName();
    private IRepository<Shape> repository = new ShapeRepository("data.txt");
    private Shape shape = null;

    @FXML
    public final void selectCircle(ActionEvent actionEvent) {
        this.shape = new Circle(border.getValue(), background.getValue(), Point2D.ZERO, sizeNumber.getValue());
    }

    @FXML
    public void selectSquare(ActionEvent actionEvent) {
        this.shape = new Square(border.getValue(), background.getValue(), Point2D.ZERO, sizeNumber.getValue());
    }

    @FXML
    public void selectTriangle(ActionEvent actionEvent) {
        this.shape = new Triangle(border.getValue(), background.getValue(), Point2D.ZERO, sizeNumber.getValue());
    }

    @FXML
    public final void drawCanvas(MouseEvent mouseEvent) {
        if (this.mainTabPane.getSelectionModel().isSelected(1)) {
            try {
                this.repository.putItem(this.factory.createShape(this.shapeName.getText()));
            }
            catch (Exception error) {
                new Alert(Alert.AlertType.ERROR, error.getMessage()).showAndWait();
            }
        }
        if(this.shape == null) return;
        this.shape.setPosition(mouseEvent.getX(), mouseEvent.getY());
        try {
            this.shape.shapeDraw(this.sheet.getGraphicsContext2D());
        }
        catch (Exception error) {
            new Alert(Alert.AlertType.ERROR, error.getMessage()).showAndWait();
        }
        this.infoShape.setText(this.shape.toString());
    }
    @FXML
    public void clearCanvas(ActionEvent actionEvent) {
        this.sheet.getGraphicsContext2D().clearRect(0, 0, this.sheet.getWidth(), this.sheet.getHeight());
        this.infoShape.setText(String.format("Определение позиции фигуры: (0, 0)"));
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
    }


}