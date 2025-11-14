package com.example.ejercicio03;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    @FXML
    private Button estilo1;

    @FXML
    private Button estilo2;

    @FXML
    private Button estilo3;

    @FXML
    private Button resetear;

    @FXML
    private Button salir;

    @FXML
    private AnchorPane fondo;

    @FXML
    private void setEstilo1(){
        fondo.getStylesheets().add(getClass().getResource("estilo1.css").toExternalForm());
    }

    @FXML
    private void setEstilo2(){
        fondo.getStylesheets().add(getClass().getResource("estilo2.css").toExternalForm());
    }

    @FXML
    private void setEstilo3(){
        fondo.getStylesheets().add(getClass().getResource("estilo3.css").toExternalForm());
    }

    @FXML
    private void setResetear(){
        fondo.getStylesheets().clear();
    }
}