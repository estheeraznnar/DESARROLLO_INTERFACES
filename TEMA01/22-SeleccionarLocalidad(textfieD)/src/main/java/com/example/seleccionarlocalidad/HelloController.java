package com.example.seleccionarlocalidad;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    private Connection con = null;
    private ResultSet rs = null;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private String nombre;

    @FXML
    private String apellidos;

    @FXML
    private String localidad;

    @FXML
    private int salario;

    @FXML
    private Button mostrar;

    @FXML
    private TableView<Empleado> tabla;

    @FXML
    private TableColumn<Empleado,String> tbNombre;

    @FXML
    private TableColumn<Empleado,String> tbApellido;

    @FXML
    private TableColumn<Empleado,String> tbLocalidad;

    @FXML
    private TableColumn<Empleado,String> tbSalario;

    // Campo donde se introduce la localidad a buscar
    @FXML
    private TextField localidadABuscar;

    @FXML
    protected void initialize() {
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";
        try{
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from datos.empleados";

            rs = stat.executeQuery(sql);
            //Comprobar que hay registros
            limpiarTabla();
            while (rs.next()) {
                // Asocia cada columna visual a un campo del objeto Empleado
                tbNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
                tbApellido.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
                tbLocalidad.setCellValueFactory(new PropertyValueFactory<>("Localidad"));
                tbSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));

                // Crea un nuevo objeto Empleado con los datos de la fila actual
                Empleado e = new Empleado(rs.getString("Nombre"),rs.getString("Apellidos"),rs.getString("Localidad"),Integer.parseInt(rs.getString("Salario")));

                // Añade el empleado a la tabla visual
                tabla.getItems().add(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Metodo que deja la tabla vacía (elimina todos los empleados visualizados)
    @FXML
    private void limpiarTabla(){
        tabla.getItems().clear();
    }

    // Busca empleados por localidad y muestra los resultados en la tabla
    @FXML
    private void buscarLocalidad(){
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";
        try{
            limpiarTabla(); // Limpia la tabla antes de buscar
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from datos.empleados where Localidad like '"+localidadABuscar.getText()+"'";

            rs = stat.executeQuery(sql);
            //Comprobar que hay registros
            while (rs.next()) { // Rellena la tabla con los empleados encontrados
                tbNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
                tbApellido.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
                tbLocalidad.setCellValueFactory(new PropertyValueFactory<>("Localidad"));
                tbSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));
                Empleado e = new Empleado(rs.getString("Nombre"),rs.getString("Apellidos"),rs.getString("Localidad"),Integer.parseInt(rs.getString("Salario")));

                tabla.getItems().add(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}