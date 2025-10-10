module com.example.datepicker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.datepicker to javafx.fxml;
    exports com.example.datepicker;
}