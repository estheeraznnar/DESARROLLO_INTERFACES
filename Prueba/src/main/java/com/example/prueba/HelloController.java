package com.example.prueba;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {


    @FXML
    private TextField nombre;

    @FXML
    private TextField nomRes;

    @FXML
    private Button boton;

    @FXML
    public void setNomRes(){
        nomRes.setText("Hola " + nombre.getText());
    }
}