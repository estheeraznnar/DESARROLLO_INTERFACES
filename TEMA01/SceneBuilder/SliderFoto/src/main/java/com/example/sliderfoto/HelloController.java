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
        slider.setMin(0);
        slider.setMax(foto.getFitWidth());
    }

    @FXML
    private void setFoto(){
        foto.setFitHeight(slider.getValue());
    }

}