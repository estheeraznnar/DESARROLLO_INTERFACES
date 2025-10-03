module com.example.sliderrgb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sliderrgb to javafx.fxml;
    exports com.example.sliderrgb;
}