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
        tbNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbApellido.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        tbLocalidad.setCellValueFactory(new PropertyValueFactory<>("Localidad"));
        tbSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";
        try {
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from datos.empleados where localidad=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,HelloController.filtro);

            rs = ps.executeQuery();
            //Comprobar que hay registros
            while (rs.next()) {
                System.out.println(rs);
                Empleado e = new Empleado(rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Localidad"), Integer.parseInt(rs.getString("Salario")));

                tabla.getItems().add(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
