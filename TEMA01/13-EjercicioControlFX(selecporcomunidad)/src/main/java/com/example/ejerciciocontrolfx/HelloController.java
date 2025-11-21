package com.example.ejerciciocontrolfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import java.sql.*;

public class HelloController {

    // Conexión y ResultSet para obtener datos de la base de datos
    private Connection con = null;
    private ResultSet rs = null;

    // Tabla y columnas para mostrar los datos de capitales
    @FXML
    private TableView<Capitales> capitales;

    @FXML
    private TableColumn<Capitales, String> provincia;

    @FXML
    private TableColumn<Capitales, String> poblacion;

    @FXML
    private TableColumn<Capitales, String> autonomia;

    // ComboBox mejorado que permite búsqueda y selección de autonomías
    @FXML
    private SearchableComboBox<String> combo;

    @FXML
    protected void initialize(){
        // Datos para conectar a la base de datos
        String url = "jdbc:mysql://localhost:3306/capitales";
        String user = "root";
        String clave = "1234";
        // Establece cómo extraer los valores de cada columna de la clase Capitales
        provincia.setCellValueFactory(new PropertyValueFactory<>("Provincia"));
        poblacion.setCellValueFactory(new PropertyValueFactory<>("Población"));
        autonomia.setCellValueFactory(new PropertyValueFactory<>("Autonomía"));

        try {
            // Realiza la conexión y obtén los datos de la tabla capitales
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from capitales";
            rs = stat.executeQuery(sql);

            // Añade todas las capitales a la TableView
            while (rs.next()) {
                Capitales c = new Capitales(rs.getString("Provincia"),rs.getString("Población"),rs.getString("Autonomía"));
                capitales.getItems().add(c);
            }
            // Llena el combo con las autonomías únicas
            rs= stat.executeQuery("Select distinct(Autonomía) From capitales.capitales");
            while (rs.next()){
                combo.getItems().add(rs.getString("Autonomía"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Cuando el usuario selecciona una autonomía en el combo, muestra solo esas capitales
    @FXML
    private void setCombo(){
        capitales.getItems().clear();// Limpia la tabla antes de mostrar los nuevos datos
        try {
            // Prepara y ejecuta una consulta para filtrar por autonomía seleccionada
            String sql = "select * from capitales.capitales where autonomía = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, combo.getValue());
            rs= ps.executeQuery();

            // Añade a la tabla solo las capitales de esa autonomí
            while (rs.next()) {
                provincia.setCellValueFactory(new PropertyValueFactory<>("Provincia"));
                autonomia.setCellValueFactory(new PropertyValueFactory<>("Autonomía"));
                poblacion.setCellValueFactory(new PropertyValueFactory<>("Población"));
                // Asegúrate que el orden de los parámetros coincide con el orden del constructor de Capitales
                Capitales c = new Capitales(
                        rs.getString("Provincia"),
                        rs.getString("Autonomía"),
                        rs.getString("Población")
                );
                capitales.getItems().add(c);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}