module org.example.ejercicio07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.ejercicio07 to javafx.fxml;
    exports org.example.ejercicio07;
}