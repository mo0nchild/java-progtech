module application.domen.task14chainresp {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.domen to javafx.fxml;
    exports application.domen;
}