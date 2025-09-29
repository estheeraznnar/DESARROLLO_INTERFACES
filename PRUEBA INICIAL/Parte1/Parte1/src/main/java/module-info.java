module com.example.parte1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parte1 to javafx.fxml;
    exports com.example.parte1;
}