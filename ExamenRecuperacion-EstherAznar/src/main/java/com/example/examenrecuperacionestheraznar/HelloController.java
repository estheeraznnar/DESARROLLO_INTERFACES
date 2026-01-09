package com.example.examenrecuperacionestheraznar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;
import java.sql.*;

public class HelloController {

    Connection con;
    private Alert alert;
    private ResultSet rs;
    private boolean nuevoProducto = false;
    private ValidationSupport v = new ValidationSupport();

    @FXML
    private TextField producto;
    @FXML
    private TextField precio;
    @FXML
    private ComboBox<String> categoria;
    @FXML
    private ComboBox<String> marca;
    @FXML
    private CheckBox wifi;
    @FXML
    private CheckBox bluetooth;
    @FXML
    private CheckBox nfc;
    @FXML
    private CheckBox g;
    @FXML
    private RadioButton nuevo;
    @FXML
    private RadioButton reacondicionado;

    @FXML
    private void initialize(){
        v.registerValidator(producto, Validator.createEmptyValidator("Se necesita un producto"));
        v.registerValidator(precio,Validator.createEmptyValidator("Debes de introducir un precio"));
        v.registerValidator(categoria,Validator.createEmptyValidator("Debes seleccionar una categoria"));
        v.registerValidator(marca,Validator.createEmptyValidator("Debes seleccionar una marca"));

        // Desactivar visualmente los errores al inicio
        v.setErrorDecorationEnabled(false);

        String url = "jdbc:mysql://localhost:3306/productos";
        String user = "root";
        String pass = "1234";

        try{
            con = DriverManager.getConnection(url,user, pass);

            //Creo un statement que permite ir de atras a delante
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "select categoria from categorias";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                categoria.getItems().add(rs.getString(1));
            }

            sql = "select marca from marcas";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                marca.getItems().add(rs.getString(1));
            }

            sql = "select * from productos.productos";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                rellenar();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void delante(){
        nuevoProducto = false;
        try {
            if (rs.next()) { // Mueve el cursor a la siguiente fila
                setEditable(false);
                rellenar();
            }else{
                atras();// Si llega al final, vuelve al último registro
                showAlert("No hay mas Registros");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void atras(){
        nuevoProducto = false;
        try {
            if (rs.previous()) { // Mueve el cursor a la fila anterior
                setEditable(false);
                rellenar();
            }else{
                delante(); // Si llega al inicio, vuelve al primer registro
                showAlert("No hay mas registros");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void primero(){
        nuevoProducto = false;
        try {
            if (rs.first()) {// Salta directamente al primer registro
                setEditable(false);
                rellenar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ultimo(){
        nuevoProducto = false;
        try {
            if (rs.last()) { // Salta directamente al último registro
                setEditable(false);
                rellenar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void nuevoProducto(){
        nuevoProducto = true;
        setEditable(true);
        // Limpio los campos
        producto.clear();
        precio.clear();
        categoria.setValue(categoria.getItems().get(0));
        marca.setValue(marca.getItems().get(0));
        wifi.setSelected(false);
        bluetooth.setSelected(false);
        nfc.setSelected(false);
        g.setSelected(false);
        nuevo.setSelected(false);
        reacondicionado.setSelected(false);
    }

    @FXML
    public void guardar(){
        v.setErrorDecorationEnabled(true);

        if (isEmpty()){
            showAlert("Hay que rellenar todos los campos");
        } else if (nuevoProducto) {
            try {

                String sql = "INSERT INTO productos (Producto,Precio,Categoria,Marca,Wifi, Bluetooth, NFC, 5G, Estado) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1,producto.getText());
                Double Precio;
                try {
                    Precio = Double.parseDouble(precio.getText());
                    stmt.setDouble(2,Precio);
                } catch (NumberFormatException e) {
                    showAlert("Hay que poner un numero decimal sin simbolos");
                    return;
                }
                stmt.setInt(3,categoria.getItems().indexOf(categoria.getValue())+1);
                stmt.setInt(4,marca.getItems().indexOf(marca.getValue())+1);

                if (wifi.isSelected()){
                    stmt.setInt(5, 1);
                }else {
                    stmt.setInt(5, 0);
                }
                if (bluetooth.isSelected()){
                    stmt.setInt(6, 1);
                }else {
                    stmt.setInt(6, 0);
                }
                if (nfc.isSelected()){
                    stmt.setInt(7, 1);
                }else {
                    stmt.setInt(7, 0);
                }
                if (g.isSelected()){
                    stmt.setInt(8, 1);
                }else {
                    stmt.setInt(8, 0);
                }

                if (nuevo.isSelected()){
                    stmt.setInt(9,0);
                }else{
                    stmt.setInt(9,1);
                }




                stmt.executeUpdate();
                showAlert("Guardado correctamente");

                Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                String sqlSelect = "select * from productos.productos";
                rs = stat.executeQuery(sqlSelect);
                rs.last();
                setEditable(false);
                nuevoProducto =false;
                rellenar();
                v.setErrorDecorationEnabled(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            showAlert("Para guardar a un producto debes crear un nuevo producto");
        }
    }

    @FXML
    public void tabla() throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(HelloApplication.class.getResource("table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage2 = new Stage();
        stage2.setTitle("Tabla");
        stage2.setScene(scene);
        stage2.show();
    }

    private void showAlert(String mensaje) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setHeaderText(mensaje);
        alert.show();
    }

    public boolean isEmpty(){
        // Verifica que los campos críticos no estén vacíos o sin marcar
        if (producto.getText().isBlank()){
            return true;
        } else if (precio.getText().isBlank()) {
            return true;
        } else if (!nuevo.isSelected() && !reacondicionado.isSelected()) {
            return true;
        } else if (!wifi.isSelected() || !bluetooth.isSelected() || !nfc.isSelected() || !g.isSelected()) {
            return true;
        }
        return false;
    }

    public void rellenar(){

        try {
            producto.setText(rs.getString(2));
            precio.setText((rs.getInt(3)) + "€");

            categoria.setValue(categoria.getItems().get(rs.getInt(4)-1));
            marca.setValue(marca.getItems().get(rs.getInt(5)-1));

            if (rs.getInt(6)==1){
                wifi.setSelected(true);
            }else {
                wifi.setSelected(false);
            }
            if (rs.getInt(7)==1){
                bluetooth.setSelected(true);
            }else {
                bluetooth.setSelected(false);
            }
            if (rs.getInt(8)==1){
                nfc.setSelected(true);
            }else {
                nfc.setSelected(false);
            }
            if (rs.getInt(9)==1){
                g.setSelected(true);
            }else {
                g.setSelected(false);
            }

            if (rs.getInt(10) == 1) {
                nuevo.setSelected(true);
                reacondicionado.setSelected(false);
            } else {
                reacondicionado.setSelected(true);
                nuevo.setSelected(false);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEditable(boolean editable){
        producto.setEditable(editable);
        precio.setEditable(editable);
        categoria.setDisable(!editable);
        marca.setDisable(!editable);
        wifi.setDisable(!editable);
        bluetooth.setDisable(!editable);
        nfc.setDisable(!editable);
        g.setDisable(!editable);
        nuevo.setDisable(!editable);
        reacondicionado.setDisable(!editable);
    }

}