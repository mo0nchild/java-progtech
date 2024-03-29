package application.domen;

import application.models.Task;
import application.services.factories.TaskDaoFactory;
import application.services.interfaces.ITaskDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final ITaskDao taskDao = TaskDaoFactory.createTaskDao(TaskDaoFactory.FactoryTypes.DATABASE);
    private ObservableList<Task> tasksData = FXCollections.observableArrayList();
    @Nullable
    private Task selectedTask = null;
    @FXML
    public TableColumn<Task, Integer> columnId, columnTime;
    @FXML
    public TableColumn<Task, String> columnName, columnStatus;
    @FXML
    public TableView<Task> tableView;
    @FXML
    public TextField nameTextField;
    @FXML
    public ComboBox<String> statusComboBox;
    @FXML
    public DatePicker timeDatePicker;

    private void showMessageBox(String message) {
        new Alert(Alert.AlertType.ERROR) {{ setTitle("Ошибка"); setContentText(message); }}.showAndWait();
    }
    private void updateTableView() {
        this.tasksData.clear();
        try { this.tasksData.addAll(this.taskDao.getAllTasks().get()); }
        catch (Exception error) { this.showMessageBox(error.getMessage()); }
    }
    private boolean checkInputControlsIsNull() {
        return this.statusComboBox.getValue() == null || this.nameTextField.getText() == null;
    }
    @FXML
    public void addButtonClickedHandler(ActionEvent actionEvent) {
        if(this.checkInputControlsIsNull()) {
            this.showMessageBox("Входные данные не установлены");
            return;
        }
        var defaultZoneId = ZoneId.systemDefault();
        var date = Date.from(this.timeDatePicker.getValue().atStartOfDay(defaultZoneId).toInstant());
        try {
            this.taskDao.addTask(new Task(0,
                    this.nameTextField.getText(),
                    Integer.parseInt(new SimpleDateFormat("ddMMyyyy").format(date)),
                    this.statusComboBox.getValue()
            )).get();
            this.updateTableView();
        }
        catch (Exception error) { this.showMessageBox(error.getMessage()); }
    }
    @FXML
    public void updateButtonClickedHandler(ActionEvent actionEvent) {
        if (this.selectedTask == null) return;
        if(this.checkInputControlsIsNull()) {
            this.showMessageBox("Входные данные не установлены");
            return;
        }
        var date = Date.from(this.timeDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        try {
            this.taskDao.updateTask(new Task(
                    this.selectedTask.getId(),
                    this.nameTextField.getText(),
                    Integer.parseInt(new SimpleDateFormat("ddMMyyyy").format(date)),
                    this.statusComboBox.getValue()
            )).get();
            this.updateTableView();
        }
        catch (Exception error) { this.showMessageBox(error.getMessage()); }
    }
    @FXML
    public void deleteButtonClickedHandler(ActionEvent actionEvent) {
        if(this.selectedTask == null) return;
        try {
            this.taskDao.deleteTask(this.selectedTask.getId()).get();
            this.updateTableView();
        }
        catch (Exception error) { this.showMessageBox(error.getMessage()); }
    }
    private void initializeTableView() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        this.columnName.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));

        this.columnTime.setCellValueFactory(new PropertyValueFactory<Task, Integer>("time"));
        this.columnStatus.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
        this.tableView.setItems(this.tasksData);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.selectedTask = this.tableView.getSelectionModel().getSelectedItem();
            if (newSelection == null) return;
            this.nameTextField.setText(newSelection.getName());
            this.statusComboBox.getSelectionModel().select(0);

            var statusList = this.statusComboBox.getItems();
            for (var index = 0; index < statusList.size(); index++) {
                if (statusList.get(index).equals(newSelection.getStatus())) {
                    this.statusComboBox.getSelectionModel().select(index);
                    break;
                }
            }
            this.timeDatePicker.setValue(LocalDate.now(ZoneId.systemDefault()));
            var formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.getDefault());
            try {
                this.timeDatePicker.setValue(LocalDate.parse(newSelection.getTime().toString(), formatter));
            }
            catch (DateTimeParseException error) {
                this.timeDatePicker.setValue(LocalDate.now(ZoneId.systemDefault()));
            }
        });
        this.statusComboBox.setItems(FXCollections.observableArrayList(Task.DONE, Task.REJECT, Task.PROGRESS));
        this.timeDatePicker.setValue(LocalDate.now(ZoneId.systemDefault()));
        this.initializeTableView();
        this.updateTableView();
    }
}