package com.example.colorrgb;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private ComboBox<Integer> Rojo;

    @FXML
    private ComboBox<Integer> Azul;

    @FXML
    private ComboBox<Integer> Verde;

    @FXML
    private TextField color;

    @FXML
    private void initialize(){
        for (int i = 0; i <= 255; i++) {
            Rojo.getItems().add(i);
            Azul.getItems().add(i);
            Verde.getItems().add(i);
        }
        Rojo.setValue(0);
        Azul.setValue(0);
        Verde.setValue(0);
        color.setBackground(Background.fill(Color.rgb(Rojo.getValue(), Azul.getValue(), Verde.getValue())));
    }

    @FXML
    private void setColor(){
        color.setBackground(Background.fill(Color.rgb(Rojo.getValue(), Azul.getValue(), Verde.getValue())));
    }

}