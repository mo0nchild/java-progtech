module application.domen.task8adao {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.domen to javafx.fxml;
    exports application.domen;
}