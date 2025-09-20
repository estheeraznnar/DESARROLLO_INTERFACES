package com.example.combobox;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private ComboBox<String> prov;

    @FXML
    private ComboBox<String> Inti;

    @FXML
    private TextField res;

    @FXML
    private Button Conf;

    @FXML
    public void initialize(){
        prov.getItems().add("Valencia");
        prov.getItems().add("Teruel");
        prov.getItems().add( "Zaragoza");
        prov.getItems().add( "Huesca");
    }

    @FXML
    public void setProv(){
        Inti.getItems().clear();
        if (prov.getValue().toString().equals("Zaragoza")){
            Inti.getItems().add("Miguel Catalan");
            Inti.getItems().add("Arcosur");
        } else if (prov.getValue().toString().equals("Huesca")) {
            Inti.getItems().add("Pirámide");
            Inti.getItems().add("Salesas");
        } else if (prov.getValue().toString().equals("Teruel")) {
            Inti.getItems().add("Segundo Chomon");
            Inti.getItems().add("Frances de Aranda");
        } else if (prov.getValue().toString().equals("Valencia")) {
            Inti.getItems().add("Jesuitas");
            Inti.getItems().add("La salle");
        }
    }

}
