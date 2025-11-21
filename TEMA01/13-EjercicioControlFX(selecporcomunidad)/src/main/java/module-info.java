module com.example.ejerciciocontrolfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.example.ejerciciocontrolfx to javafx.fxml;
    exports com.example.ejerciciocontrolfx;
}