module com.example.ejercicio06juegodecalculo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens com.example.ejercicio06juegodecalculo to javafx.fxml;
    exports com.example.ejercicio06juegodecalculo;
}