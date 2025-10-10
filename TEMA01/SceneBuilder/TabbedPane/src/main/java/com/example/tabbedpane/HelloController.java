package com.example.tabbedpane;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private TextField nombre;

    @FXML
    private TextField apellido;

    @FXML
    private CheckBox coche;

    @FXML
    private CheckBox monovol;

    @FXML
    private Tab pestaña1;

    @FXML
    private Tab pestaña2;

    @FXML
    private Tab pestaña3;

    @FXML
    private void initialize(){
        pestaña2.setDisable(true);
        pestaña3.setDisable(true);
    }
    @FXML
    private void setCoche(){
        if (coche.isSelected()){
            pestaña2.setDisable(false);
        }else {
            pestaña2.setDisable(true);
        }

        if (monovol.isSelected()){
            pestaña3.setDisable(false);
        }else {
            pestaña3.setDisable(true);
        }
    }
}