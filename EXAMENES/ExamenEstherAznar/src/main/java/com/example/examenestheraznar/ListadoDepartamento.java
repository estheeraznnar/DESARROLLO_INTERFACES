package com.example.examenestheraznar;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class ListadoDepartamento {
    @FXML
    private Connection con;// Objeto Connection para conectarse a la base de datos
    @FXML
    private Statement stat;  // Permite ejecutar consultas SQL
    @FXML
    private ResultSet rs;   // Almacena el resultado de una consulta SQL
    @FXML
    private TableView<Empleado> tbEmpleado;

     @FXML
    private TableColumn<Empleado, String> tbNombre;
     @FXML
    private TableColumn<Empleado, String> tbSalario;
     @FXML
    private TableColumn<Empleado, String> tbSalarioNeto;
     @FXML
    private TableColumn<Empleado, String> tbDepatamento;

    @FXML
    protected void initialize() {
        // Se asocian las columnas de la tabla a las propiedades de Empleado (los nombres deben coincidir)
        tbNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbDepatamento.setCellValueFactory(new PropertyValueFactory<>("Departamento"));
        tbSalarioNeto.setCellValueFactory(new PropertyValueFactory<>("Salario Neto"));
        tbSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));

        // Parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String clave = "1234";
        try {
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select Nombre, Departamento, Salario, Salario/0.15 as SalarioNeto from empresa.empleados;";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,HelloController.filtro);
            // Ejecuta la consulta y obtiene los resultados
            rs = ps.executeQuery();
            //Comprobar que hay registros
            Double salarioneto = Integer.parseInt(rs.getString("Salario"))/0.15;
            while (rs.next()) {
                System.out.println(rs);// Imprime el ResultSet en la consola (puede usarse para debug)
                Empleado e = new Empleado(rs.getString("Nombre"), rs.getString("Departamento"), Integer.parseInt(rs.getString("Salario")), salarioneto);

                tbEmpleado.getItems().add(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
