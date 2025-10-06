module com.example.ventanaprincipal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ventanaprincipal to javafx.fxml;
    exports com.example.ventanaprincipal;
}