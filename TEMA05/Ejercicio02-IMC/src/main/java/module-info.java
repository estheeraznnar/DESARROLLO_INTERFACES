module com.example.ejercicio02imc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens com.example.ejercicio02imc to javafx.fxml;
    exports com.example.ejercicio02imc;
}