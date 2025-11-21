module com.example.menubar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.menubar to javafx.fxml;
    exports com.example.menubar;
}