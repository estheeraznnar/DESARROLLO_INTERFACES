module com.example.ejer01capitales {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejer01capitales to javafx.fxml;
    exports com.example.ejer01capitales;
}