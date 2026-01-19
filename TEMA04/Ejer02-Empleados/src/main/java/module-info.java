module com.example.ejer01capitales {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens com.example.ejer01capitales to javafx.fxml;
    exports com.example.ejer01capitales;
}