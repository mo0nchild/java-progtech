module application.domen.task7observer {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.domen to javafx.fxml;
    exports application.domen;
}