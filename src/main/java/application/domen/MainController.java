package application.domen;

import application.models.Procent;
import application.models.ProcentException;
import application.models.Procentable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final Procentable calculator = new Procent();
    @FXML
    public Spinner<Integer> inputValue;
    @FXML
    public VBox calculateProcent_15, calculateProcent_10, calculateProcent_3;
    @FXML
    public Label resultValue;

    private final MainController setLabelText(TextFlow textList, Calculatable method) {
        if(textList.getChildren().get(1) instanceof Text children) {
            try {
                children.setText(String.format("%.2f (руб)", method.calculate()));
            }
            catch (ProcentException error) {
                new Alert(Alert.AlertType.ERROR, "Метод [" + error.getMethodName() + "]: " + error.getMessage()).showAndWait();
            }
        }
        return this;
    }
    @FXML
    public void calculateProcent(ActionEvent actionEvent) {
        final var source = (Node) actionEvent.getSource();
        final var userData = (String) source.getUserData();

        final var procentValue = Integer.parseInt(userData.split("_")[1]);
        this.calculator.setValue(this.inputValue.getValue().floatValue());

        if (source.getScene().lookup(userData) instanceof VBox resultView) {
            final var boxList = resultView.getChildren().stream().map(item -> (TextFlow)item).toList();
            try {
                this.setLabelText(boxList.get(0), () -> this.calculator.countPr(procentValue))
                        .setLabelText(boxList.get(1), () -> this.calculator.countSum(this.inputValue.getValue().floatValue(), procentValue))
                        .setLabelText(boxList.get(2), () -> this.calculator.countSumrnd(procentValue).floatValue());

                this.resultValue.setText(String.format("Чек: %.2f (руб)", calculator.countPr(procentValue) + this.calculator.getValue()));
            }
            catch (ProcentException error) {
                new Alert(Alert.AlertType.ERROR, "Метод " + error.getMethodName() + ": " + error.getMessage()).showAndWait();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}