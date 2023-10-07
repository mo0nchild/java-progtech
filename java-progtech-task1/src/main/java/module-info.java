module application.javaprogtechtask1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    opens application.domen to javafx.fxml;
    exports application.domen;
    exports application.models;
}