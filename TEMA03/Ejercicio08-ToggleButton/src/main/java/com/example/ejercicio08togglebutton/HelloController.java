package com.example.ejercicio08togglebutton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HelloController {
    @FXML
    ToggleGroup color;
    @FXML
    ToggleGroup fuente;
    @FXML
    ToggleGroup tamano;
    @FXML
    Label texto;

    @FXML
    ToggleButton red;
    @FXML
    ToggleButton green;
    @FXML
    ToggleButton blue;

    @FXML
    ToggleButton verdana;
    @FXML
    ToggleButton tahoma;
    @FXML
    ToggleButton courier;

    @FXML
    ToggleButton t10;
    @FXML
    ToggleButton t20;
    @FXML
    ToggleButton t30;

    @FXML
    Button ayuda;


    private void initialize(){
        List<String> fontFamilies = Font.getFamilies();
        List<String> fontNames    = Font.getFontNames();
        for(String item : fontFamilies) {
            System.out.println(item);
        }
        for(String item : fontNames) {
            System.out.println(item);
        }
    }

    @FXML
    private void rojo() {
        texto.setBackground(new Background(new
                BackgroundFill(Paint.valueOf("red"),null,null)));
    }

    @FXML
    private void verde() {
        texto.setBackground(new Background(new
                BackgroundFill(Paint.valueOf("green"),null,null)));
    }

    @FXML
    private void azul() {
        texto.setBackground(new Background(new
                BackgroundFill(Paint.valueOf("blue"),null,null)));
    }

    @FXML
    private void verd() {
        texto.setFont(Font.font("Verdana"));
    }

    @FXML
    private void taho() {
        texto.setFont(Font.font("Tahoma"));
    }

    @FXML
    private void cour() {
        texto.setFont(Font.font("Courier"));
    }

    @FXML
    private void tam10() {
        texto.setFont(Font.font(10));
    }

    @FXML
    private void tam20() {
        texto.setFont(Font.font(20));
    }

    @FXML
    private void tam30() {
        texto.setFont(Font.font(30));
    }

    @FXML
    private void setAyuda() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("Ejercicio08-ToggleButton.chm"));
    }
}