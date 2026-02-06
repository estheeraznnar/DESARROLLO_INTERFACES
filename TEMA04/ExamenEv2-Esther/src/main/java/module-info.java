module org.example.examenev2esther {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.examenev2esther to javafx.fxml;
    exports org.example.examenev2esther;
}