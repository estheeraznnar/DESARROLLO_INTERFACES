package com.example.ejercicio07estilosmenu;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    @FXML
    private  MenuBar menuBar;

    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;

    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void setMenuItem1(){
        anchorPane.getStylesheets().add(getClass().getResource("estilo07-1.css").toExternalForm());
    }
    @FXML
    private void setMenuItem2(){
        anchorPane.getStylesheets().add(getClass().getResource("estilo07-2.css").toExternalForm());
    }
    @FXML
    private void setMenuItem3(){
        anchorPane.getStylesheets().clear();
    }

}