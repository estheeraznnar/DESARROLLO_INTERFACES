module com.example.gridpaneclinicadental {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gridpaneclinicadental to javafx.fxml;
    exports com.example.gridpaneclinicadental;
}