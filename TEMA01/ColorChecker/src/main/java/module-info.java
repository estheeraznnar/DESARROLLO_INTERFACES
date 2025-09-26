module com.example.colorchecker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorchecker to javafx.fxml;
    exports com.example.colorchecker;
}