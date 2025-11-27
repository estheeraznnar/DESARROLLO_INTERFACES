package com.example.examenestheraznar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloController {

    @FXML
    private Connection con;// Objeto Connection para conectarse a la base de datos
    @FXML
    private Statement stat;  // Permite ejecutar consultas SQL
    @FXML
    private ResultSet rs;   // Almacena el resultado de una consulta SQL
    // Variable estática utilizada como filtro para otras ventanas o controladores
    public static String filtro;
    private Stage stage2;
    @FXML
    private DateTimeFormatter formatter;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtSalario;

    @FXML
    private  DatePicker fechaNacimiento;

    @FXML
    private RadioButton mujer;

    @FXML
    private RadioButton hombre;

    @FXML
    private ComboBox<String> cb_departamento;

    @FXML
    private MenuItem nuevo;

    @FXML
    private MenuItem guardar;
    @FXML
    private MenuItem primero;
    @FXML
    private MenuItem ultimo;
    @FXML
    private MenuItem anterior;
    @FXML
    private MenuItem siguiente;
    @FXML
    private MenuItem tabla;


    @FXML
    private void initialize(){

        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String clave = "1234";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            con = DriverManager.getConnection(url, user, clave);
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from empresa.empleados";
            rs = stat.executeQuery(sql); // Ejecuta la consulta
            if (rs.next()) {   // Si hay resultados, muestra el primero
                txtNombre.setText(rs.getString("Nombre"));
                txtSalario.setText(rs.getString("Salario"));
                if (rs.getBoolean("Sexo") != true){
                    hombre.setSelected(rs.getBoolean("Sexo"));
                    mujer.isDisable();
                }else{
                    mujer.setSelected(rs.getBoolean("Sexo"));
                    hombre.isDisable();
                }

                cb_departamento.setValue(rs.getString("Departamento"));
                fechaNacimiento.setValue(rs.getDate("FechaNac").toLocalDate());
            }

            rs= stat.executeQuery("Select distinct(Departamento) From empresa.empleados");
            while (rs.next()){
                cb_departamento.getItems().add(rs.getString("Departamento"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void setNuevo(){
        txtNombre.clear();
        txtSalario.clear();
        hombre.setSelected(false);
        mujer.setSelected(false);
        cb_departamento.setValue("");
       // fechaNacimiento.
    }

    @FXML
    private void setGuardar(){
        try {

            String sql = "INSERT INTO empleados (Nombre, FechaNac, Salario, Departamento) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtNombre.getText());
            ps.setDate(2, Date.valueOf(fechaNacimiento.getValue()));

//            if (hombre.isSelected()){
//                ps.setInt(3, Integer.parseInt( hombre.getId()));
//            }else{
//                ps.setInt(3,Integer.parseInt( mujer.getId()));
//            }

            ps.setDouble(3,Double.parseDouble(txtSalario.getText()));
            ps.setInt(4, Integer.parseInt(cb_departamento.getValue()));
            ps.executeUpdate();
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql1 = "select * from empresa.empleados";
            rs = stat.executeQuery(sql1);



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Va al primer registro del ResultSet y lo muestra
    @FXML
    private void principio() throws SQLException {

        String sql = "select * from empresa.empleados";
        rs = stat.executeQuery(sql);
        try {
            if (rs.first()) {   // Si hay resultados, muestra el primero
                txtNombre.setText(rs.getString("Nombre"));
                txtSalario.setText(rs.getString("Salario"));
                if (rs.getBoolean("Sexo") != true){
                    hombre.setSelected(rs.getBoolean("Sexo"));
                    mujer.isDisable();
                }else{
                    mujer.setSelected(rs.getBoolean("Sexo"));
                    hombre.isDisable();
                }

                cb_departamento.setValue(rs.getString("Departamento"));
                fechaNacimiento.setValue(rs.getDate("FechaNac").toLocalDate());
            }

            rs= stat.executeQuery("Select distinct(Departamento) From empresa.empleados");
            while (rs.next()){
                cb_departamento.getItems().add(rs.getString("Departamento"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Va al último registro y lo muestra
    @FXML
    private void alfinal() throws SQLException {
        String sql = "select * from empresa.empleados";
        rs = stat.executeQuery(sql);
            try {
                if (rs.last()) {
                    txtNombre.setText(rs.getString("Nombre"));
                    txtSalario.setText(rs.getString("Salario"));
                    if (rs.getBoolean("Sexo") != true){
                        hombre.setSelected(rs.getBoolean("Sexo"));
                        mujer.isDisable();
                    }else{
                        mujer.setSelected(rs.getBoolean("Sexo"));
                        hombre.isDisable();
                    }

                    cb_departamento.setValue(rs.getString("Departamento"));
                    fechaNacimiento.setValue(rs.getDate("FechaNac").toLocalDate());
                }

                rs= stat.executeQuery("Select distinct(Departamento) From empresa.empleados");
                while (rs.next()){
                    cb_departamento.getItems().add(rs.getString("Departamento"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }

    // Va al registro siguiente y lo muestra; si no hay más, muestra alerta
    @FXML
    private void derecha() throws SQLException {

        String sql = "select * from empresa.empleados";
        rs = stat.executeQuery(sql);
        try {
            if (rs.first()) {   // Si hay resultados, muestra el primero
                txtNombre.setText(rs.getString("Nombre"));
                txtSalario.setText(rs.getString("Salario"));
                if (rs.getBoolean("Sexo") != true){
                    hombre.setSelected(rs.getBoolean("Sexo"));
                    mujer.isDisable();
                }else{
                    mujer.setSelected(rs.getBoolean("Sexo"));
                    hombre.isDisable();
                }

                cb_departamento.setValue(rs.getString("Departamento"));
                fechaNacimiento.setValue(rs.getDate("FechaNac").toLocalDate());
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Ya estas en el último");
            }

            rs= stat.executeQuery("Select distinct(Departamento) From empresa.empleados");
            while (rs.next()){
                cb_departamento.getItems().add(rs.getString("Departamento"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Va al registro anterior y lo muestra; si no hay más atrás, muestra alerta
    @FXML
    private void izquierda() throws SQLException {

        String sql = "select * from empresa.empleados";
        rs = stat.executeQuery(sql);
        try {
            if (rs.previous()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtSalario.setText(rs.getString("Salario"));
                if (rs.getBoolean("Sexo") != true){
                    hombre.setSelected(rs.getBoolean("Sexo"));
                    mujer.isDisable();
                }else{
                    mujer.setSelected(rs.getBoolean("Sexo"));
                    hombre.isDisable();
                }

                cb_departamento.setValue(rs.getString("Departamento"));
                fechaNacimiento.setValue(rs.getDate("FechaNac").toLocalDate());
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Ya estas en el primero");
            }

            rs= stat.executeQuery("Select distinct(Departamento) From empresa.empleados");
            while (rs.next()){
                cb_departamento.getItems().add(rs.getString("Departamento"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    private void setTabla() throws IOException {
        // Carga el FXML "nueva.fxml" para una nueva ventana
        FXMLLoader fxmlLoader = new
                FXMLLoader(HelloApplication.class.getResource("ListadoDepartamento.fxml"));
        Scene scene = null;
        scene = new Scene(fxmlLoader.load(), 320, 240);

        // Crea y muestra la nueva ventana
        stage2 = new Stage();
        stage2.setTitle("Nueva ventana");
        stage2.setScene(scene);
        stage2.show();
    }

}