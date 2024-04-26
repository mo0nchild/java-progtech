module application.domen.taskunittest {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.domen.taskunittest to javafx.fxml;
    exports application.domen.taskunittest;
}