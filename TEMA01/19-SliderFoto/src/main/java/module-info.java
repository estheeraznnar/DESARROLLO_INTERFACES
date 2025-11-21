module com.example.sliderfoto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sliderfoto to javafx.fxml;
    exports com.example.sliderfoto;
}