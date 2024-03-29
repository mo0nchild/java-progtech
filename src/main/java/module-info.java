module application.domen.task8adao {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires org.postgresql.jdbc;
    requires org.jetbrains.annotations;

    opens application.domen to javafx.fxml;
    opens application.models to com.fasterxml.jackson.databind;
    exports application.domen;
    exports application.models;
}