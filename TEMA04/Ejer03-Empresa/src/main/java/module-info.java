module com.example.ejer03empresa {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.sf.jasperreports.core;
    requires java.sql;


    opens com.example.ejer03empresa to javafx.fxml;
    exports com.example.ejer03empresa;
}