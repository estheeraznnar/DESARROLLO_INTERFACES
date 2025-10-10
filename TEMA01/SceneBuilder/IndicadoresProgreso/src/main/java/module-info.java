module com.example.indicadoresprogreso {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.indicadoresprogreso to javafx.fxml;
    exports com.example.indicadoresprogreso;
}