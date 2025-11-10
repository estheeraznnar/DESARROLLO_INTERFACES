module com.example.ejercicio01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio01 to javafx.fxml;
    exports com.example.ejercicio01;
}