package com.example.colorpiker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HelloController {
    @FXML
    private ColorPicker color1;

    @FXML
    private ColorPicker color2;

    @FXML
    private Button boton;

    @FXML
    private TextField textoColor;

    @FXML
    private Label letras;

    @FXML
    private void setColor1(){
        textoColor.setBackground(new Background(
                new BackgroundFill(color1.getValue(), null, null)));
    }

    @FXML
    private void setColor2(){
        letras.setTextFill(color2.getValue());
    }
}