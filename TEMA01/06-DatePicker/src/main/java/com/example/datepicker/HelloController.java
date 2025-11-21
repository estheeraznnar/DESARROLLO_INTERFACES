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
        //pongo de que formato quiero que salga
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @FXML
    public void setFecha(){
        //coge la fecha y la pongo en el txt
        texto.setText(fecha.getValue().format(formatter));
    }
}