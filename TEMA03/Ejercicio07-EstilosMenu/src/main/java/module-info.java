module com.example.ejercicio07estilosmenu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens com.example.ejercicio07estilosmenu to javafx.fxml;
    exports com.example.ejercicio07estilosmenu;
}