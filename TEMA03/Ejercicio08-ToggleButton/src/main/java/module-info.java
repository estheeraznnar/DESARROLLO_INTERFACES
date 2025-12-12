module com.example.ejercicio08togglebutton {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens com.example.ejercicio08togglebutton to javafx.fxml;
    exports com.example.ejercicio08togglebutton;
}