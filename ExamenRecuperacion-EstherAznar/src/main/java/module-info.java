module com.example.examenrecuperacionestheraznar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens com.example.examenrecuperacionestheraznar to javafx.fxml;
    exports com.example.examenrecuperacionestheraznar;
}