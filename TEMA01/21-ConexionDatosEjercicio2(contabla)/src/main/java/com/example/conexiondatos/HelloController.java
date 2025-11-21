package com.example.conexiondatos;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class HelloController {
    @FXML
    private Connection con =null;
    @FXML
    private Statement stat;

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
    private Button anadir;

    @FXML
    private TableView<Empleado> tabla;

    @FXML
    private TableColumn<Empleado, String> Cnombre;
    @FXML
    private TableColumn<Empleado, String> Capellidos;
    @FXML
    private TableColumn<Empleado, String> Clocalidad;
    @FXML
    private TableColumn<Empleado, Integer> Csalario;

    @FXML
    protected void initialize() {
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String clave = "1234";



        try {
            con = DriverManager.getConnection(url, user, clave);
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from datos.empleados";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                nombre.setText(rs.getString("Nombre"));
                apellido.setText(rs.getString("Apellidos"));
                loc.setText(rs.getString("Localidad"));
                salario.setText(rs.getString("Salario"));

                // Asocia las columnas a las propiedades de Empleado
                Cnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                Capellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
                Clocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
                Csalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
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
    private void setGuardar(){

        try {
            String sql = "INSERT INTO empleados VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre.getText());
            ps.setString(2, apellido.getText());
            ps.setString(3, loc.getText());
            ps.setInt(4,Integer.parseInt(salario.getText()));
            ps.executeUpdate();
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql1 = "select * from datos.empleados";
            rs = stat.executeQuery(sql1);



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void setNuevo() {

        nombre.setText("");
        apellido.setText("");
        loc.setText("");
        salario.setText("");

    }

    // Añade un nuevo empleado a la tabla leyendo los datos de los TextField
    @FXML
    private void addEmpleado() {
        Empleado p = new Empleado(
                nombre.getText(),
                apellido.getText(),
                loc.getText(),
                Integer.parseInt(salario.getText())
        );
        tabla.getItems().add(p);
        // Opcional: limpiar los campos tras añadir
        nombre.clear();
        apellido.clear();
        loc.clear();
        salario.clear();
    }

}