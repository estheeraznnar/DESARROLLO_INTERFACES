module org.example.ejercicio01aplicacioncalculos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens org.example.ejercicio01aplicacioncalculos to javafx.fxml;
    exports org.example.ejercicio01aplicacioncalculos;
}