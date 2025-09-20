package com.example.ejercicio1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField num1;

    @FXML
    private TextField num2;

    @FXML
    private TextField res;

    @FXML
    private Button sumar;

    @FXML
    private Button Restar;

    @FXML
    private Button Multiplicar;

    @FXML
    public void setSumar(){
        Integer n1 = Integer.valueOf(num1.getText());
        Integer n2 = Integer.valueOf(num2.getText());
        Integer su = n1 + n2;
        res.setText(Integer.toString(su));
    }

    @FXML
    public void setRestar(){
        Integer n1 = Integer.valueOf(num1.getText());
        Integer n2 = Integer.valueOf(num2.getText());
        Integer re = n1 - n2;
        res.setText(Integer.toString(re));
    }

    @FXML
    public void setMultiplicar(){
        Integer n1 = Integer.valueOf(num1.getText());
        Integer n2 = Integer.valueOf(num2.getText());
        Integer mu = n1 * n2;
        res.setText(Integer.toString(mu));
    }
}