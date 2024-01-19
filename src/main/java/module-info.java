module application.domen.task8decorator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens application.domen to javafx.fxml;
    exports application.domen;
}