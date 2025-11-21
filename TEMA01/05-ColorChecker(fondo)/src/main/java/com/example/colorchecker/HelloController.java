package com.example.colorchecker;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;

public class HelloController {
    @FXML
    private ComboBox<Integer> tamaño;

    @FXML
    private ColorPicker color1;

    @FXML
    private ColorPicker color2;

    @FXML
    private Label texto;

    @FXML
    private void initialize(){
        //pone por en el combo de tamaño que solo puede ser de 10 a 72
        for (int i = 10; i < 72; i++) {
            tamaño.getItems().add(i);
        }
    }

    @FXML
    private void setColor1(){
        //coge el color y lo pone de fondo del label
        texto.setBackground(new Background(new BackgroundFill
                (color1.getValue(), null, null)));
    }

    @FXML
    private void setColor2(){
        //coge el color y lo pone en las letras del label
        texto.setTextFill(color2.getValue());
    }

    @FXML
    private void setTamaño(){
        //Pone el tamaño de letra seleccionado en el combo box
        //tiene que ser asi
        int t = Integer.parseInt(tamaño.getValue().toString());
        texto.setFont(new Font(t));
    }
}