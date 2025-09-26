module com.example.colorpiker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorpiker to javafx.fxml;
    exports com.example.colorpiker;
}