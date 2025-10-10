package com.example.datepicker;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class HelloController {
    @FXML
    private DatePicker fecha;

    @FXML
    private TextField texto;

    @FXML
    private DateTimeFormatter formatter;

    @FXML
    public void initialize(){
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @FXML
    public void setFecha(){
        texto.setText(fecha.getValue().format(formatter));
    }
}