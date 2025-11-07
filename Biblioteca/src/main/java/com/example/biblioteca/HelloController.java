package com.example.biblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private TextField tx_titulo;

    @FXML
    private TextField tx_autor;

    @FXML
    private TextField tx_isbn;

    @FXML
    private TextField tx_paginas;

    @FXML
    private ComboBox<String> co_genero;

    @FXML
    private CheckBox che_dispo;

    @FXML
    private Button derecha;

    @FXML
    private Button doble_derecha;

    @FXML
    private Button izquierda;

    @FXML
    private Button doble_izquierda;

}