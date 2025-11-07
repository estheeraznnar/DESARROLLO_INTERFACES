module com.example.listado_productos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.listado_productos to javafx.fxml;
    exports com.example.listado_productos;
}