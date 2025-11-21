package com.example.seleccionarlocalidad;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Nueva {
    private Connection con = null;
    private ResultSet rs = null;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private TableColumn<Empleado,String> tbNombre;

    @FXML
    private TableColumn<Empleado,String> tbApellido;

    @FXML
    private TableColumn<Empleado,String> tbLocalidad;

    @FXML
    private TableColumn<Empleado,String> tbSalario;
    @FXML
    private TableView<Empleado> tabla;

    @FXML
    protected void initialize() {
        // Se asocian las columnas de la tabla a las propiedades de Empleado (los nombres deben coincidir)
        tbNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbApellido.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        tbLocalidad.setCellValueFactory(new PropertyValueFactory<>("Localidad"));
        tbSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));

        // Parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";
        try {
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // Consulta parametrizada para buscar empleados por localidad
            String sql = "select * from datos.empleados where localidad=?;";
            PreparedStatement ps = con.prepareStatement(sql);

            // Obtiene el filtro (localidad a buscar) desde una variable pública/estática de otro controlador
            ps.setString(1,HelloController.filtro);
            // Ejecuta la consulta y obtiene los resultados
            rs = ps.executeQuery();
            //Comprobar que hay registros
            while (rs.next()) {
                System.out.println(rs);// Imprime el ResultSet en la consola (puede usarse para debug)
                Empleado e = new Empleado(rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Localidad"), Integer.parseInt(rs.getString("Salario")));

                tabla.getItems().add(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
