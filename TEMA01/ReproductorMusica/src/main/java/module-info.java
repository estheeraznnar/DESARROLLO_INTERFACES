module org.example.reproductormusica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.example.reproductormusica to javafx.fxml;
    exports org.example.reproductormusica;
}