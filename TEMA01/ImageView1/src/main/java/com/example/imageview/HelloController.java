package com.example.imageview;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private ImageView imagen;

    @FXML
    private ComboBox<String> desp;

    @FXML
    private void initialize(){
        desp.getItems().add("Perro");
        desp.getItems().add("Gato");
    }

    @FXML
    private void setImagen(){
        if (desp.getValue().toString().equals("Perro")){
            /*Image image = new Image(getClass().getResource("img/perro.jpg").toExternalForm());
            imagen.setImage(image);*/
            imagen.setImage(new Image(getClass().getResource("img/perro.jpg").toExternalForm()));
        }else if (desp.getValue().toString().equals("Gato")){
            /*Image image = new Image(getClass().getResource("img/gato.jpg").toExternalForm());
            imagen.setImage(image);*/
            imagen.setImage(new Image(getClass().getResource("img/gato.jpg").toExternalForm()));
        }

    }
}