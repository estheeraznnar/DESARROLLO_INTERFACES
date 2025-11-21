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
        // Llena cada ComboBox con valores de 0 a 255
        // (todos los valores posibles para un canal de color RGB)
        for (int i = 0; i <= 255; i++) {
            Rojo.getItems().add(i);
            Azul.getItems().add(i);
            Verde.getItems().add(i);
        }
        // Inicializa todos a 0
        Rojo.setValue(0);
        Azul.setValue(0);
        Verde.setValue(0);
        // Establece el fondo del TextField 'color' al color construido con los valores seleccionados
        color.setBackground(Background.fill(
                Color.rgb(
                        Rojo.getValue(),
                        Azul.getValue(),
                        Verde.getValue()
                )
        ));
    }

    // Metodo que debes llamar (por ejemplo, con el evento setOnAction en cada ComboBox)
    // Cambia el fondo del TextField al nuevo color según la selección
    @FXML
    private void setColor(){
        color.setBackground(Background.fill(Color.rgb(Rojo.getValue(), Azul.getValue(), Verde.getValue())));
    }

}