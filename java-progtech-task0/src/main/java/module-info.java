module application.javaprogtechtask0 {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.domen to javafx.fxml;
    exports application.domen;
    exports application.models;
}