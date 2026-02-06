module org.example.ejercicio7parametrosfotografico {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.ejercicio7parametrosfotografico to javafx.fxml;
    exports org.example.ejercicio7parametrosfotografico;
}