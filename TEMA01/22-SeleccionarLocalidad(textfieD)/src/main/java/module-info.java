module com.example.seleccionarlocalidad {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.seleccionarlocalidad to javafx.fxml;
    exports com.example.seleccionarlocalidad;
}