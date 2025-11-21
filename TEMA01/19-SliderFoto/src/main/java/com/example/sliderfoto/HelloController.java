package com.example.sliderfoto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private Slider slider;

    @FXML
    private ImageView foto;

    @FXML
    private void initialize(){
        slider.setMin(0); // El valor mínimo del slider es 0 (sin altura visible)
        slider.setMax(foto.getFitWidth());// El máximo es igual al ancho actual de la imagen
    }

    // Metodo que se ejecuta cada vez que el usuario mueve el slider
    @FXML
    private void setFoto(){
        // Cambia la altura de la imagen en función del valor actual del slider
        foto.setFitHeight(slider.getValue());
    }

}