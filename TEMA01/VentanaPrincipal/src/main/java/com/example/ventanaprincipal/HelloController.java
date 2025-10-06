package com.example.ventanaprincipal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apell;
    @FXML
    private TextField dni;
    @FXML
    private TextField fecha;
    @FXML
    private TextField consulta;
    @FXML
    private CheckBox check;
    @FXML
    private Button boton;
    @FXML
    private Button informacion;

    @FXML
    private void setInformacion() throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(HelloApplication.class.getResource("ventana1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage2 = new Stage();
        stage2.setTitle("Ventana 1");
        stage2.setScene(scene);
        stage2.show();
    }
    @FXML
    private void setCheck() throws IOException {
        if (check.isSelected()){
            FXMLLoader fxmlLoader = new
                    FXMLLoader(HelloApplication.class.getResource("ventana2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage2 = new Stage();
            stage2.setTitle("Ventana 2");
            stage2.setScene(scene);
            stage2.show();
        }
    }



}