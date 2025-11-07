package com.example.listado_productos;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private TextField tx_nombre;

    @FXML
    private TextField tx_unidad;

    @FXML
    private TextField tx_precio;

    @FXML
    private Button btn_agregar;

    @FXML
    private Button btn_nuevo;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TableColumn<Producto, String > tb_nombre;

    @FXML
    private TableColumn<Producto, Float > tb_unidad;

    @FXML
    private TableColumn<Producto, Float > tb_precio;

    @FXML
    private TableColumn<Producto, Float > tb_subtotal;

    @FXML
    private void setBtn_agregar(){
        tb_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tb_unidad.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        tb_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tb_subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        Producto p = new Producto(tx_nombre.getText(),Float.parseFloat(tx_unidad.getText()), Float.parseFloat(tx_precio.getText()) ,(Float.parseFloat(tx_precio.getText()) * Float.parseFloat(tx_unidad.getText())));
        tabla.getItems().add(p);
    }

    @FXML
    private void setBtn_nuevo(){
        tx_nombre.clear();
        tx_unidad.clear();
        tx_precio.clear();
    }
}