module org.iesch.examen_ud1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens org.iesch.examen_ud1 to javafx.fxml;
    exports org.iesch.examen_ud1;
}