module application.domen.task7observer {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.domen.task7observer to javafx.fxml;
    exports application.domen.task7observer;
}