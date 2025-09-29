package com.example.parte1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField texto;

    @FXML
    private Button boton;

    @FXML
    private ComboBox<String> lista;

    @FXML
    private void setBoton(){
        lista.getItems().add(texto.getText());
        texto.clear();

    }

}