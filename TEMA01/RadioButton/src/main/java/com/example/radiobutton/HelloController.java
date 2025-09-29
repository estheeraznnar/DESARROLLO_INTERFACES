package com.example.radiobutton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private RadioButton Zaragoza;

    @FXML
    private  RadioButton Teruel;

    @FXML
    private  RadioButton Huesca;

    @FXML
    private TextField selec;

    @FXML
    public void setSelec(){
        if (Zaragoza.isSelected()){
            selec.setText("Zaragoza");
        } else if (Teruel.isSelected()) {
            selec.setText("Teruel");
        } else if (Huesca.isSelected()) {
            selec.setText("Huesca");
        }
    }

    //ToggleButtonn
}