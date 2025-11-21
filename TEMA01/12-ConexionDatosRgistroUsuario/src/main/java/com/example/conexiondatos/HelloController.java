package com.example.conexiondatos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {
    // Conexión y gestión de la base de datos
    @FXML
    private Connection con;// Objeto Connection para conectarse a la base de datos
    @FXML
    private Statement stat;  // Permite ejecutar consultas SQL
    @FXML
    private ResultSet rs;   // Almacena el resultado de una consulta SQL

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField loc;
    @FXML
    private TextField salario;
    @FXML
    private Button btn1; // Navegación (ej: primero)
    @FXML
    private Button btn2; // Navegación (ej: anterior)
    @FXML
    private Button btn3;// Navegación (ej: siguiente)
    @FXML
    private Button btn4;// Navegación (ej: último)
    @FXML
    private Button nuevo;// Vacía los campos para añadir un nuevo empleado
    @FXML
    private Button guardar; // Guarda un nuevo empleado en la base de datos

    @FXML
    protected void initialize() {
        //se conecta a la base de datos al arrancar el controlador y carga el primer empleado
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";

        try {
            con = DriverManager.getConnection(url, user, clave);
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from datos.empleados";
            rs = stat.executeQuery(sql); // Ejecuta la consulta
            if (rs.next()) {   // Si hay resultados, muestra el primero
                nombre.setText(rs.getString("Nombre"));
                apellido.setText(rs.getString("Apellidos"));
                loc.setText(rs.getString("Localidad"));
                salario.setText(rs.getString("Salario"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Va al primer registro del ResultSet y lo muestra
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

    // Va al último registro y lo muestra
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

    // Va al registro siguiente y lo muestra; si no hay más, muestra alerta
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

    // Va al registro anterior y lo muestra; si no hay más atrás, muestra alerta
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

    // Guarda el contenido de los TextFields como nuevo registro en la base de datos
    @FXML
    private void setGuardar(){

        try {
            String sql = "INSERT INTO empleados (nombre, apellidos, localidad, salario) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);// Preparar consulta segura
            ps.setString(1, nombre.getText());
            ps.setString(2, apellido.getText());
            ps.setString(3, loc.getText());
            ps.setInt(4,Integer.parseInt(salario.getText()));
            ps.executeUpdate();// Ejecutar inserción

            // Vuelve a cargar todos los datos, para actualizar el ResultSet y poder navegar
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql1 = "select * from datos.empleados";
            rs = stat.executeQuery(sql1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Limpia todos los campos de texto para poder insertar uno nuevo
    @FXML
    private void setNuevo() {

        nombre.setText("");
        apellido.setText("");
        loc.setText("");
        salario.setText("");

    }



}