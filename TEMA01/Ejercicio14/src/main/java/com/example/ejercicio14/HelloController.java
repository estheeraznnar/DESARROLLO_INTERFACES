package com.example.ejercicio14;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {

    public static String filtro;
    private Stage stage2;
    @FXML
    private Connection con;
    @FXML
    private Statement stat;

    @FXML
    private ResultSet rs;
    @FXML
    private TextField txnombre;

    @FXML
    private TextField txprecio;

    @FXML
    private Button izq;

    @FXML
    private Button dobleizq;

    @FXML
    private Button drch;

    @FXML
    private Button dobledrch;

    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnMostrar;

    @FXML
    private void initialize(){
        String url = "jdbc:mysql://localhost:3306/piezas";
        String user = "root";
        String clave = "1234";


        try {
            con = DriverManager.getConnection(url, user, clave);
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from piezas.piezas";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                txnombre.setText(rs.getString("Nombre"));
                txprecio.setText(rs.getString("Precio"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void principio(){
        try {
            if (rs.first()){
                txnombre.setText(rs.getString("Nombre"));
                txprecio.setText(rs.getString("Precio"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void alfinal(){
        try {
            if (rs.last()){
                txnombre.setText(rs.getString("Nombre"));
                txprecio.setText(rs.getString("Precio"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void derecha(){
        try {
            if (rs.next()){
                txnombre.setText(rs.getString("Nombre"));
                txprecio.setText(rs.getString("Precio"));
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Ya estas en el último");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setIzq(){
        try {
            if (rs.previous()){
                txnombre.setText(rs.getString("Nombre"));
                txprecio.setText(rs.getString("Precio"));
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Ya estas en el primero");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setBtnGuardar(){
        try {
            String sql = "Insert into piezas.piezas (nombre, precio) Values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txnombre.getText());
            ps.setFloat(2, Float.parseFloat(txprecio.getText()));
            ps.executeUpdate();
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql1 = "select * from piezas.piezas";
            rs = stat.executeQuery(sql1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setBtnAñadir(){
        txnombre.setText("");
        txprecio.setText("");
    }
    
    @FXML
    private void setBtnMostrar(){
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(HelloApplication.class.getResource("tabla.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 320, 240);

            stage2 = new Stage();
            stage2.setTitle("Nueva ventana");
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}