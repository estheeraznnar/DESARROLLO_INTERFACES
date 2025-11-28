package com.example.ejercicio06juegodecalculo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn5;
    @FXML
    private Button btn7;
    @FXML
    private Button btn10;
    @FXML
    private Button btn15;
    @FXML
    private Button btn20;
    @FXML
    private Button btn25;
    @FXML
    private Button btn50;
    @FXML
    private Button btn60;
    @FXML
    private Button btn75;
    @FXML
    private Button btn100;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnInstrucciones;

    @FXML
    private TextField txSiguiente;
    @FXML
    private TextField txNumero;

    Integer numero = 0;

    @FXML
    private void initialize(){
        setBtnNewGame();
        btn1.setOnAction(num->{
            numero += 1;
            txNumero.setText(numero.toString());
            btn1.setDisable(true);
            alerta();
        });
        btn2.setOnAction(num->{
            numero += 2;
            txNumero.setText(numero.toString());
            btn2.setDisable(true);
            alerta();
        });
        btn5.setOnAction(num->{
            numero += 5;
            txNumero.setText(numero.toString());
            btn5.setDisable(true);
            alerta();
        });
        btn7.setOnAction(num->{
            numero += 7;
            txNumero.setText(numero.toString());
            btn7.setDisable(true);
            alerta();
        });
        btn10.setOnAction(num->{
            numero += 10;
            txNumero.setText(numero.toString());
            btn10.setDisable(true);
            alerta();
        });
        btn15.setOnAction(num->{
            numero += 15;
            txNumero.setText(numero.toString());
            btn15.setDisable(true);
            alerta();
        });
        btn20.setOnAction(num->{
            numero += 20;
            txNumero.setText(numero.toString());
            btn20.setDisable(true);
            alerta();
        });
        btn25.setOnAction(num->{
            numero += 25;
            txNumero.setText(numero.toString());
            btn25.setDisable(true);
            alerta();
        });
        btn50.setOnAction(num->{
            numero += 50;
            txNumero.setText(numero.toString());
            btn50.setDisable(true);
            alerta();
        });
        btn60.setOnAction(num->{
            numero += 60;
            txNumero.setText(numero.toString());
            btn60.setDisable(true);
            alerta();
        });btn75.setOnAction(num->{
            numero += 75;
            txNumero.setText(numero.toString());
            btn75.setDisable(true);
            alerta();
        });btn100.setOnAction(num->{
            numero += 100;
            txNumero.setText(numero.toString());
            btn100.setDisable(true);
            alerta();
        });
    }

    @FXML
    private void setBtnNewGame() {
        Integer numeros = (int) (Math.random() * 200) + 1;
        txSiguiente.setText(numeros.toString());
        txNumero.clear();
        numero=0;
        txNumero.setDisable(true);
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn5.setDisable(false);
        btn7.setDisable(false);
        btn10.setDisable(false);
        btn15.setDisable(false);
        btn20.setDisable(false);
        btn25.setDisable(false);
        btn50.setDisable(false);
        btn60.setDisable(false);
        btn75.setDisable(false);
        btn100.setDisable(false);
    }

    private void alerta(){
        if (Integer.parseInt(txSiguiente.getText()) == Integer.parseInt(txNumero.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.show();
            alert.setTitle("Esto es el título");
            alert.setHeaderText("HAS ACERTADO!!");
            alert.setContentText("Has Acertado");
        } else if (Integer.parseInt(txSiguiente.getText()) < Integer.parseInt(txNumero.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.show();
            alert.setTitle("Esto es el título");
            alert.setHeaderText("HAS FALLADO!!");
            alert.setContentText("Has Fallado");
        }
    }

}