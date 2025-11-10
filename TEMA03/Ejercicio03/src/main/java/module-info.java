module com.example.ejercicio03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio03 to javafx.fxml;
    exports com.example.ejercicio03;
}