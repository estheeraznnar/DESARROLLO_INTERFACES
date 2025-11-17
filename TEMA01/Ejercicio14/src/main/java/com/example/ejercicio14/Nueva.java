package com.example.ejercicio14;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class Nueva{

    private Connection con = null;
    private ResultSet rs = null;

    @FXML
    private TableColumn<Piezas, Integer> tbId;

    @FXML
    private TableColumn<Piezas, String> tbNombre;

    @FXML
    private TableColumn<Piezas, Float> tbPrecio;

    @FXML
    private TableView<Piezas> tabla;

    @FXML
    private void initialize(){
        tbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        String url = "jdbc:mysql://localhost:3306/piezas";
        String user = "root";
        String clave = "1234";

        try {
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from piezas.piezas;";
            PreparedStatement ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            //Comprobar que hay registros
            while (rs.next()) {
                System.out.println(rs);
                Piezas p = new Piezas(Integer.parseInt(rs.getString("Id")), rs.getString("Nombre"),Float.parseFloat(rs.getString("Precio")));

                tabla.getItems().add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
