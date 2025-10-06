module com.example.tabbedpane {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tabbedpane to javafx.fxml;
    exports com.example.tabbedpane;
}