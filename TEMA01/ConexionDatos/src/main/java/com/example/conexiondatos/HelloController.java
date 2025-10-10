package com.example.conexiondatos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {

    @FXML
    private ResultSet rs;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField loc;
    @FXML
    private TextField salario;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button nuevo;
    @FXML
    private Button guardar;

    @FXML
    protected void initialize() {
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";


        try {
            Connection con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from datos.empleados";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                nombre.setText(rs.getString("Nombre"));
                apellido.setText(rs.getString("Apellidos"));
                loc.setText(rs.getString("Localidad"));
                salario.setText(rs.getString("Salario"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void principio(){
        try{
            if (rs.first()){
            nombre.setText(rs.getString("Nombre"));
            apellido.setText(rs.getString("Apellidos"));
            loc.setText(rs.getString("Localidad"));
            salario.setText(rs.getString("Salario"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void alfinal(){
        try{
            if (rs.last()){
                nombre.setText(rs.getString("Nombre"));
                apellido.setText(rs.getString("Apellidos"));
                loc.setText(rs.getString("Localidad"));
                salario.setText(rs.getString("Salario"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void derecha(){

        try{
            if (rs.next()){
                nombre.setText(rs.getString("Nombre"));
                apellido.setText(rs.getString("Apellidos"));
                loc.setText(rs.getString("Localidad"));
                salario.setText(rs.getString("Salario"));
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Ya estas en el último");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void izquierda(){

        try{
            if (rs.previous()){
                nombre.setText(rs.getString("Nombre"));
                apellido.setText(rs.getString("Apellidos"));
                loc.setText(rs.getString("Localidad"));
                salario.setText(rs.getString("Salario"));
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Ya estas en el primero");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void setNuevo() {
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";


        try {
            Connection con = DriverManager.getConnection(url, user, clave);
            PreparedStatement ps = con.prepareStatement("Insert into empleado (nombre, apellido, localizacion, salario) values ?,?,?,?");
            ps.setString(1, nombre.getText());
            ps.setString(2, apellido.getText());
            ps.setString(3, loc.getText());
            ps.setString(4,salario.getText());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}