module application.javaprogtechtask2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    opens application.domen to javafx.fxml;
    exports application.domen;
    exports application.commons;
    exports application.models;
}