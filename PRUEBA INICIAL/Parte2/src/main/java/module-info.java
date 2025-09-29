module com.example.parte2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parte2 to javafx.fxml;
    exports com.example.parte2;
}