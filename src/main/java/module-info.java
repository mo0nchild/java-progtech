module com.example.asdasd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.asdasd to javafx.fxml;
    exports com.example.asdasd;
}