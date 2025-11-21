package com.example.ejercicio14;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {

    @FXML
    private TextField tfTitulo, tfAutor, tfISBN, tfPaginas, tfGenero;
    @FXML
    private CheckBox cbDisponible;
    @FXML
    private Button btnPrimero, btnAnterior, btnSiguiente, btnUltimo, btnMostrarGeneros;

    private Connection con = null;
    private Statement stat = null;
    private ResultSet rs = null;

    @FXML
    private void initialize() {
        String url = "jdbc:mysql://localhost:3306/biblioteca";
        String user = "root";
        String clave = "1234";
        try {
            con = DriverManager.getConnection(url, user, clave);
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stat.executeQuery("SELECT l.*, g.Nombre as Genero FROM libros l LEFT JOIN genero g ON l.Genero=g.Id");
            if (rs.next()) cargarLibro();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarLibro() throws SQLException {
        tfTitulo.setText(rs.getString("Titulo"));
        tfAutor.setText(rs.getString("Autor"));
        tfISBN.setText(rs.getString("ISBN"));
        tfPaginas.setText(rs.getString("Paginas"));
        tfGenero.setText(rs.getString("Genero"));
        cbDisponible.setSelected(rs.getInt("Disponible") == 1);
    }

    @FXML private void primero() { try { if (rs.first()) cargarLibro(); } catch (SQLException e) {} }
    @FXML private void anterior() { try { if (rs.previous()) cargarLibro(); } catch (SQLException e) {} }
    @FXML private void siguiente() { try { if (rs.next()) cargarLibro(); } catch (SQLException e) {} }
    @FXML private void ultimo() { try { if (rs.last()) cargarLibro(); } catch (SQLException e) {} }

    @FXML
    private void mostrarGeneros() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("generos.fxml"));
            stage.setScene(new javafx.scene.Scene(loader.load(), 300, 200));
            stage.setTitle("Listado de Géneros");
            stage.show();
        } catch (Exception ex) { throw new RuntimeException(ex); }
    }
}