package com.example.seleccionarlocalidad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    // Variable estática utilizada como filtro para otras ventanas o controladores
    public static String filtro;

    // Stage adicional para abrir ventanas secundarias
    private Stage stage2;
    private Connection con = null;
    private ResultSet rs = null;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    // Variables (no controles de JavaFX) para guardar los datos de un empleado
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
            while (rs.next()) {// Por cada fila encontrada, mapea columnas y añade a la tabla
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

    @FXML
    private void limpiarTabla(){
        tabla.getItems().clear();
    }

    @FXML
    private void buscarLocalidad(){// Metodo que ejecuta la búsqueda y abre una nueva ventana con los resultados
        try {
            // Guarda el valor del filtro para ser reutilizado en otras ventanas/controladores
            filtro = localidadABuscar.getText();
            // Si hay ya una ventana secundaria abierta, la cierra primero
            if (stage2!=null){
                stage2.close();
            }

            // Carga el FXML "nueva.fxml" para una nueva ventana
            FXMLLoader fxmlLoader = new
                    FXMLLoader(HelloApplication.class.getResource("nueva.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 320, 240);

            // Crea y muestra la nueva ventana
            stage2 = new Stage();
            stage2.setTitle("Nueva ventana");
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}