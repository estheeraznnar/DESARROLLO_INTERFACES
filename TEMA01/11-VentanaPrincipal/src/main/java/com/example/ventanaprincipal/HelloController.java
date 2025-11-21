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

    // Metodo que se ejecuta cuando el usuario pulsa en el botón de 'información'
    @FXML
    private void setInformacion() throws IOException {
        // Carga de un nuevo archivo FXML (ventana1.fxml) para abrir otra ventana
        FXMLLoader fxmlLoader = new
                FXMLLoader(HelloApplication.class.getResource("ventana1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);// Crea una escena con ese FXML
        Stage stage2 = new Stage();  // Crea una nueva ventana (Stage)
        stage2.setTitle("Ventana 1");// Pone título a la ventana
        stage2.setScene(scene); // Asigna la escena a la nueva ventana
        stage2.show(); // Muestra la ventana
    }
    @FXML
    private void setCheck() throws IOException {
        // Si el checkbox está seleccionado, carga y muestra la ventana2
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