package com.example.examenrecuperacionestheraznar;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import java.sql.*;

public class TableController {


    private Connection conn;
    private ResultSet rs;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TableColumn<Producto, String> tbprducto;

    @FXML
    private TableColumn<Producto, Double>  tbprecio;

    @FXML
    private TableColumn<Producto, String> tbcategoria;

    @FXML
    private TableColumn<Producto, String> tbmarca;

    @FXML
    private  TableColumn<Producto, String> tbestado;

    @FXML
    private SearchableComboBox<String> comboBoxCategoria;

    @FXML
    private void initialize(){
        tbprducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        tbprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tbmarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tbestado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        String url = "jdbc:mysql://localhost:3306/productos";
        String user = "root";
        String clave = "1234";

        try {
            conn = DriverManager.getConnection(url, user, clave);

            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);



            String sqlProducto = "select p.producto, p.precio, c.categoria, p.marca, p.estado from productos p join categorias c on c.id = p.categoria;";
            rs = statement.executeQuery(sqlProducto);


            while (rs.next()) {
                Producto p = new Producto(rs.getString(1),rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5));
                tabla.getItems().add(p);
            }

            String sql = "select distinct(categoria) from categorias";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                comboBoxCategoria.getItems().add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setComboBoxCategoria(){
        tabla.getItems().clear();
        try {

            String sql = "select p.producto, p.precio, c.categoria, p.marca, p.estado from productos p join categorias c on c.id = p.categoria where c.categoria = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, comboBoxCategoria.getValue());
            rs= ps.executeQuery();


            while (rs.next()) {
                tbprducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
                tbprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
                tbcategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
                tbmarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
                tbestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
                // Asegúrate que el orden de los parámetros coincide con el orden del constructor de Capitales
                Producto p = new Producto(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                tabla.getItems().add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
