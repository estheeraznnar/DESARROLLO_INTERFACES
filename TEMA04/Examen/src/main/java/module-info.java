module org.example.examenpablonavarrocamacho {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.examenpablonavarrocamacho to javafx.fxml;
    exports org.example.examenpablonavarrocamacho;
}