package org.iesch.examen_ud1;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class TableController {

    // --- Atributos de Conexión ---
    private Connection conn;
    private ResultSet rs;
    private ResultSet rsDep; // Reservado para posibles consultas de departamentos

    // --- Componentes de la Interfaz (TableView) ---
    @FXML
    private TableView<Empleado> tabla; // La tabla que contendrá objetos de tipo Empleado

    @FXML
    private TableColumn<Empleado, String> cNombre; // Columna para el nombre

    @FXML
    private TableColumn<Empleado, Double> cSalario; // Columna para el salario bruto

    @FXML
    private TableColumn<Empleado, Double> cSalarioNeto; // Columna para el salario calculado (neto)

    @FXML
    private TableColumn<Empleado, String> cDepartamento; // Columna para el nombre del departamento

    @FXML
    public void initialize(){
        // 1. Vincular las columnas de la tabla con los atributos de la clase 'Empleado'
        // PropertyValueFactory busca automáticamente los métodos getNombre(), getSalario(), etc.
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        cSalarioNeto.setCellValueFactory(new PropertyValueFactory<>("salarioNeto"));
        cDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));

        // 2. Configuración de la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String clave = "1234";

        try {
            // Establecer conexión
            conn = DriverManager.getConnection(url, user, clave);
            Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // 3. Consulta SQL con JOIN para obtener el nombre del departamento en lugar de su ID
            String sqlEmpleado = "select e.nombre, e.salario, d.departamento from empleados e join departamentos d on d.id = e.departamento";
            rs = stat.executeQuery(sqlEmpleado);

            // 4. Iterar sobre los resultados de la BD
            while (rs.next()) {
                // Crear una nueva instancia de la clase Empleado por cada fila
                // rs.getString(1) -> nombre, rs.getDouble(2) -> salario, rs.getString(3) -> nombre departamento
                Empleado p = new Empleado(rs.getString(1),rs.getDouble(2), rs.getString(3));
                // Añadir el objeto empleado a la lista de la tabla para que se muestre en pantalla
                tabla.getItems().add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
