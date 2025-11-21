package com.example.sliderrgb;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Slider rojo;

    @FXML
    private Slider verde;

    @FXML
    private Slider azul;

    @FXML
    private TextField color;

    @FXML
    private void initialize(){
        // Ajusta los rangos de cada slider de 0 a 255 (valores estándar RGB)
        rojo.setMin(0);
        rojo.setMax(255);
        azul.setMin(0);
        azul.setMax(255);
        verde.setMin(0);
        verde.setMax(255);
        // Inicializa el fondo del campo de texto usando los valores actuales de los sliders
        color.setBackground(Background.fill( Color.rgb((int) rojo.getValue(), (int) azul.getValue(), (int) verde.getValue())));
    }

    // Metodo que se debe llamar cada vez que alguno de los sliders cambia de valor
    @FXML
    private void setColor(){
        color.setBackground(Background.fill( Color.rgb((int) rojo.getValue(), (int) azul.getValue(), (int) verde.getValue())));
    }
}