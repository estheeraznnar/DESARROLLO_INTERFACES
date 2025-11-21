package com.example.ejercicio14;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenerosController {
    @FXML
    private TableView<Genero> tablaGeneros;
    @FXML private TableColumn<Genero, Integer> colId;
    @FXML private TableColumn<Genero, String> colNombre;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        String url = "jdbc:mysql://localhost:3306/biblioteca";
        try (Connection con = DriverManager.getConnection(url, "root", "1234");
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery("SELECT * FROM genero")) {
            while (rs.next()) {
                tablaGeneros.getItems().add(new Genero(rs.getInt("Id"), rs.getString("Nombre")));
            }
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
