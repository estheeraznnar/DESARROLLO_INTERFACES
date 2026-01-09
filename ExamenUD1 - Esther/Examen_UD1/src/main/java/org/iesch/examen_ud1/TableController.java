package org.iesch.examen_ud1;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class TableController {

    private Connection conn;
    private ResultSet rs;
    private ResultSet rsDep;

    @FXML
    private TableView<Empleado> tabla;

    @FXML
    private TableColumn<Empleado,String> cNombre;

    @FXML
    private TableColumn<Empleado,Double> cSalario;

    @FXML
    private TableColumn<Empleado,Double> cSalarioNeto;

    @FXML
    private TableColumn<Empleado,String> cDepartamento;

    @FXML
    public void initialize(){
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        cSalarioNeto.setCellValueFactory(new PropertyValueFactory<>("salarioNeto"));
        cDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String clave = "1234";

        try {
            conn = DriverManager.getConnection(url, user, clave);
            Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sqlEmpleado = "select e.nombre, e.salario, d.departamento from empleados e join departamentos d on d.id = e.departamento";
            rs = stat.executeQuery(sqlEmpleado);
            while (rs.next()) {
                Empleado p = new Empleado(rs.getString(1),rs.getDouble(2), rs.getString(3));
                tabla.getItems().add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
