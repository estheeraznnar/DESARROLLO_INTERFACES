module com.example.ejercicio1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio1 to javafx.fxml;
    exports com.example.ejercicio1;
}