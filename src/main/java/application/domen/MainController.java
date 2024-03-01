package application.domen;

import application.actions.FieldAction;
import application.handlers.CollectHandler;
import application.handlers.EmptyHandler;
import application.handlers.LoseHandler;
import application.models.FieldModel;
import application.models.HandlerModel;
import application.services.actions.GenerateField;
import application.services.actions.IGenerateField;
import application.services.handlers.IHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final int FIELD_WIDTH = 6, FIELD_HEIGHT = 6;
    private static final int START_MONET = 3;

    private IHandler fieldHandler;
    private FieldAction fieldAction;
    private Integer monetCount = START_MONET;
    @FXML
    public Canvas canvas;
    @FXML
    public Label monetLabel;

    protected void handler(FieldModel.FieldCell fieldCell) {
        var requestState = new HandlerModel.RequestState(monetCount);
        var response = this.fieldHandler.handle(new HandlerModel(fieldCell.getCellType(), requestState));

        if (response == IHandler.HandlerResponse.NOT_HANDLED) {
            throw new RuntimeException("Обработчик не был найден");
        }
        if (requestState.getTermination()) {
            this.fieldAction.setAllCellsChecked();
            var alert = new Alert(Alert.AlertType.CONFIRMATION) {{
                setTitle("Подтверждение");
                setHeaderText("Начать заново игру?");
            }};
            var result = alert.showAndWait();
            if (result.get() != ButtonType.OK) {
                var mainWindow = this.canvas.getScene().getWindow();
                ((Stage)mainWindow).close();
            }
            else this.fieldAction.initializeField(FIELD_WIDTH, FIELD_HEIGHT);
        }
        this.setMonet(requestState.getMonetCount());
    }
    protected void setMonet(int monet) {
        this.monetLabel.setText(String.format("Количество монет: %s", this.monetCount = monet));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.fieldAction = new FieldAction(new GenerateField(), canvas);
        this.fieldHandler = new EmptyHandler(new CollectHandler(new LoseHandler(null)));

        this.fieldAction.addListener(this::handler);
        this.fieldAction.initializeField(FIELD_WIDTH, FIELD_HEIGHT);
    }
}