module com.example.radiobutton {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.radiobutton to javafx.fxml;
    exports com.example.radiobutton;
}