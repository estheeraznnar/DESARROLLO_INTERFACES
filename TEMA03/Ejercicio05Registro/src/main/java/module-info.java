module com.example.ejercicio05registro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio05registro to javafx.fxml;
    exports com.example.ejercicio05registro;
}