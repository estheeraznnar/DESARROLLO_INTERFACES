package com.example.seleccionarlocalidad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML private TextField nombre;
    @FXML private TextField apellidos;
    @FXML private DatePicker fechaEvento;
    @FXML private Spinner<Integer> duracion;
    @FXML private Spinner<Integer> invitados;
    @FXML private RadioButton menuPicoteo;
    @FXML private RadioButton menu1;
    @FXML private RadioButton menu2;
    @FXML private CheckBox discoMovil;
    @FXML private CheckBox animInfantil;
    @FXML private CheckBox transporte;
    @FXML private TextField total;
    @FXML private Button calcular;
    private ToggleGroup grupoMenus;

    @FXML
    private void initialize() {
        // Spinner duración: valores de 1 a 4 horas, por defecto 1
        duracion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1));
        // Spinner invitados: valores de 1 a 500 (o lo que quieras)
        invitados.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500, 1));
        // Grupo de radio menú
        grupoMenus = new ToggleGroup();
        menuPicoteo.setToggleGroup(grupoMenus);
        menu1.setToggleGroup(grupoMenus);
        menu2.setToggleGroup(grupoMenus);
        menuPicoteo.setSelected(true); // Por defecto
        total.setEditable(false);
    }

    @FXML
    private void calcularTotal() {
        int numInvitados = invitados.getValue();
        int horas = duracion.getValue();
        float precioMenu = 0;

        // Determinar precio menú seleccionado
        if (menuPicoteo.isSelected()) precioMenu = 10;
        else if (menu1.isSelected()) precioMenu = 15;
        else if (menu2.isSelected()) precioMenu = 20;

        float subtotalMenus = precioMenu * numInvitados;
        float extras = 0;
        if (discoMovil.isSelected()) extras += 200;
        if (animInfantil.isSelected()) extras += 100;
        // Transporte solo si mínimo 10 personas y 1 hora
        if (transporte.isSelected() && numInvitados >= 10 && horas >= 1) {
            extras += 5 * numInvitados * horas;
        }

        float totalEvento = subtotalMenus + extras;
        total.setText(String.format("%.2f €", totalEvento));
    }
}