module application.domen.taskunittest {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires batik.awt.util;

    opens application.domen to javafx.fxml;
    exports application.domen;
}