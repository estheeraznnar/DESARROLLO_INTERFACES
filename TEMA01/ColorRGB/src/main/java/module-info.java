module com.example.colorrgb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorrgb to javafx.fxml;
    exports com.example.colorrgb;
}