module com.example.validation {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    opens com.example.validation to javafx.fxml;
    exports com.example.validation;
}