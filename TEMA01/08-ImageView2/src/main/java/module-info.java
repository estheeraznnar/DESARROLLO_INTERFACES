module com.example.imageview2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.imageview2 to javafx.fxml;
    exports com.example.imageview2;
}