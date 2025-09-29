package com.example.parte2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label hola;

    @FXML
    private Button saluda;

    @FXML
    private Button noSaluda;

    @FXML
    private void initialize(){
        hola.setVisible(false);
    }

    @FXML
    private void setSaluda(){
        hola.setVisible(true);
    }

    @FXML
    private void setNoSaluda(){
        hola.setVisible(false);
    }

}