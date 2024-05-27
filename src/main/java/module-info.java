module application.domen.task13composite {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.domen to javafx.fxml;
    exports application.domen;
}