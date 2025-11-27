module com.example.examenestheraznar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens com.example.examenestheraznar to javafx.fxml;
    exports com.example.examenestheraznar;
}