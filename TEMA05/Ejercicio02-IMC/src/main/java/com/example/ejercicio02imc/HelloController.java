package com.example.ejercicio02imc;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Spinner<Integer> spinner1;

    @FXML
    private Spinner<Integer> spinner2;

    @FXML
    private Button reiniciar;

    @FXML
    private Button calcular;
    @FXML
    private Button informacion;

    @FXML
    private TextField textField;

    @FXML
    private void initialize(){
        SpinnerValueFactory<Integer> factory1 = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 400, 50, 1);
        spinner1.setValueFactory(factory1);

        SpinnerValueFactory<Integer> factory2 = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 250, 50, 1);
        spinner2.setValueFactory(factory2);
        textField.setDisable(true);

    }

    @FXML
    private void setTextField(){
        double peso = spinner1.getValue();
        double altura = spinner2.getValue();
        double alturaM = altura/100;

        double imc = (peso/(alturaM*alturaM));

        textField.setText(String.format("%.2f", imc));
    }

    @FXML
    private void setReiniciar(){
        textField.clear();
    }

    @FXML
    private void setInformacion() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("CalculadoraIMC.chm"));
    }
}