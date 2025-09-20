module com.example.togglebutton {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.togglebutton to javafx.fxml;
    exports com.example.togglebutton;
}