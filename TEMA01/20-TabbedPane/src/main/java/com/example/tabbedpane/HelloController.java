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
        // Al iniciar, solo la primera pestaña está habilitada
        pestaña2.setDisable(true);
        pestaña3.setDisable(true);
    }

    // Metodo que se activa, normalmente, cuando cambia el estado de los CheckBox
    @FXML
    private void setCoche(){
        // Si la casilla de 'coche' está seleccionada, habilita la segunda pestaña; si no, la deshabilita
        if (coche.isSelected()){
            pestaña2.setDisable(false);
        }else {
            pestaña2.setDisable(true);
        }

        // Si la casilla de 'monovol' está seleccionada, habilita la tercera pestaña; si no, la deshabilita
        if (monovol.isSelected()){
            pestaña3.setDisable(false);
        }else {
            pestaña3.setDisable(true);
        }
    }
}