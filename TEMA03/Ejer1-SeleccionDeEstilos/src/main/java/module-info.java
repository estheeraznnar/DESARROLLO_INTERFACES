module com.example.ejer1selecciondeestilos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejer1selecciondeestilos to javafx.fxml;
    exports com.example.ejer1selecciondeestilos;
}