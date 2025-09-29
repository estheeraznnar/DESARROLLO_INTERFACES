module com.example.combobox {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.combobox to javafx.fxml;
    exports com.example.combobox;
}