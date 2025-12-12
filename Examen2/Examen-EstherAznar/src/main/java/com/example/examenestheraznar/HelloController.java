package com.example.examenestheraznar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    Label titulo;

    @FXML
    private  MenuBar menuBar;
    @FXML
    MenuItem estilo1;
    @FXML
    MenuItem estilo2;
    @FXML
    MenuItem sinEstilo;
    @FXML
    MenuItem documentacion;

    @FXML
    ImageView imageView;

    @FXML
    Label etiquetas;

    @FXML
    ToggleButton fb;
    @FXML
    ToggleButton amb;
    @FXML
    ToggleButton pvb;
    @FXML
    ToggleButton nb;

    @FXML
    ToggleButton atiende;

    @FXML
    RadioButton fr;
    @FXML
    RadioButton amr;
    @FXML
    RadioButton pvr;
    @FXML
    RadioButton nr;

    @FXML
    Slider slider;
    @FXML
    ProgressBar progressBar;
    @FXML
    ProgressIndicator progressIndicator;
    @FXML
    AnchorPane anchorPane;

    @FXML
    private void initialize(){
        slider.setMin(0);
        slider.setMax(75);

    }

    @FXML
    private void setRadiof(){
        if (fr.isSelected()){
            progressIndicator.setProgress(progressIndicator.getProgress()+1);
            amr.setDisable(true);
            nr.setDisable(true);
            pvr.setDisable(true);
        } else{
            progressIndicator.setProgress(0);
            amr.setDisable(false);
            nr.setDisable(false);
            pvr.setDisable(false);
        }
    }
    @FXML
    private void setRadioam(){
       if (amr.isSelected()) {
            progressIndicator.setProgress(progressIndicator.getProgress()+0.75);
            fr.setDisable(true);
            nr.setDisable(true);
            pvr.setDisable(true);
        }else {
           progressIndicator.setProgress(0);
            fr.setDisable(false);
            nr.setDisable(false);
            pvr.setDisable(false);
        }
    }

    @FXML
    private void setRadiopv(){
        if (pvr.isSelected()) {
            progressIndicator.setProgress(progressIndicator.getProgress()+0.50);
            amr.setDisable(true);
            nr.setDisable(true);
            fr.setDisable(true);
        }else{
            progressIndicator.setProgress(0);
            fr.setDisable(false);
            nr.setDisable(false);
            amr.setDisable(false);
        }
    }

    @FXML
    private void setRadion(){
        if (nr.isSelected()) {
            progressIndicator.setProgress(progressIndicator.getProgress()+0.25);
            amr.setDisable(true);
            pvr.setDisable(true);
            fr.setDisable(true);
        }else {
            progressIndicator.setProgress(0);
            fr.setDisable(false);
            pvr.setDisable(false);
            amr.setDisable(false);
        }
    }

    @FXML
    private void setBarraf(){
        if (fb.isSelected()){
            progressBar.setProgress(progressBar.getProgress()+1);
            amb.setDisable(true);
            pvb.setDisable(true);
            nb.setDisable(true);
        }else{
            progressBar.setProgress(0);
            amb.setDisable(false);
            pvb.setDisable(false);
            nb.setDisable(false);
        }
    }
    @FXML
    private void setBarraam(){
        if (amb.isSelected()){
            progressBar.setProgress(progressBar.getProgress()+0.75);
            fb.setDisable(true);
            pvb.setDisable(true);
            nb.setDisable(true);
        }else{
            progressBar.setProgress(0);
            fb.setDisable(false);
            pvb.setDisable(false);
            nb.setDisable(false);
        }
    }
    @FXML
    private void setBarrapv(){
        if (pvb.isSelected()){
            progressBar.setProgress(progressBar.getProgress()+0.50);
            fb.setDisable(true);
            amb.setDisable(true);
            nb.setDisable(true);
        }else{
            progressBar.setProgress(0);
            fb.setDisable(false);
            amb.setDisable(false);
            nb.setDisable(false);
        }
    }

    @FXML
    private void setBarran(){
        if (nb.isSelected()){
            progressBar.setProgress(progressBar.getProgress()+0.25);
            fb.setDisable(true);
            amb.setDisable(true);
            pvb.setDisable(true);
        }else{
            progressBar.setProgress(0);
            fb.setDisable(false);
            amb.setDisable(false);
            pvb.setDisable(false);
        }
    }

    @FXML
    private void setSlider(){
        if (slider.getValue()<=25){
            imageView.setImage(new Image(getClass().getResource("img/nivel1.jpg").toExternalForm()));
        } else if (slider.getValue()>25 && slider.getValue()<=50) {
            imageView.setImage(new Image(getClass().getResource("img/nivel2.jpg").toExternalForm()));
        } else if (slider.getValue()>50) {
            imageView.setImage(new Image(getClass().getResource("img/nivel3.jpg").toExternalForm()));
        }
    }

    @FXML
    private void setEstilo1(){
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(getClass().getResource("estilo1.css").toExternalForm());
    }
    @FXML
    private void setEstilo2(){
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(getClass().getResource("estilo2.css").toExternalForm());
    }
    @FXML
    private void setSinEstilo(){
        anchorPane.getStylesheets().clear();
    }
    @FXML
    private void setayuda() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("AyudaExamen.chm"));
    }
}