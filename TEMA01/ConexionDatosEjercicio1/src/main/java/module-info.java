module com.example.conexiondatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.conexiondatos to javafx.fxml;
    exports com.example.conexiondatos;
}