module org.example.ejercicio8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.ejercicio8 to javafx.fxml;
    exports org.example.ejercicio8;
}