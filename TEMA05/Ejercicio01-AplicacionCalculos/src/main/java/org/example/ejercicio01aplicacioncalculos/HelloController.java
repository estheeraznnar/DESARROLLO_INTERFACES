package org.example.ejercicio01aplicacioncalculos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class HelloController {

    // ===== CAMPOS FXML - TextFields =====
    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;

    @FXML
    private TextField txtResultado;

    // ===== CAMPOS FXML - Botones =====
    @FXML
    private Button btnSumar;

    @FXML
    private Button btnRestar;

    @FXML
    private Button btnMultiplicar;

    // ===== CAMPOS FXML - MenuItems =====
    @FXML
    private MenuItem menuSalir;

    @FXML
    private MenuItem menuLimpiar;

    @FXML
    private MenuItem menuPdf;

    @FXML
    private MenuItem menuChm;

    @FXML
    private MenuItem menuWeb;


    // ===== MÉTODOS DE EVENTOS =====

    // Suma los dos números
    @FXML
    private void handleSumar(ActionEvent event) {
        realizarOperacion("sumar");
    }

    // Resta los dos números
    @FXML
    private void handleRestar(ActionEvent event) {
        realizarOperacion("restar");
    }

    // Multiplica los dos números
    @FXML
    private void handleMultiplicar(ActionEvent event) {
        realizarOperacion("multiplicar");
    }

    // Cierra la aplicación
    @FXML
    private void handleSalir(ActionEvent event) {
        System.exit(0);
    }

    // Limpia todos los campos
    @FXML
    private void handleLimpiar(ActionEvent event) {
        txtNumero1.clear();
        txtNumero2.clear();
        txtResultado.clear();
    }

    // Abre el archivo PDF de ayuda
    @FXML
    private void handleAyudaPdf(ActionEvent event) {
        abrirArchivo("Ayuda.pdf");
    }

    // Abre el archivo CHM de ayuda
    @FXML
    private void handleAyudaChm(ActionEvent event) {
        abrirArchivo("Ayuda.chm");
    }

    // Abre la página web de ayuda
    @FXML
    private void handleAyudaWeb(ActionEvent event) {
        abrirWeb("https://ejemplo.com/ayuda");
    }


    // ===== MÉTODOS AUXILIARES =====

    // Ejecuta la operación indicada y muestra el resultado
    private void realizarOperacion(String operacion) {
        try {
            // Convierte los textos a números
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = Double.parseDouble(txtNumero2.getText());
            double resultado = 0;

            // Realiza la operación según el tipo
            switch (operacion) {
                case "sumar":
                    resultado = num1 + num2;
                    break;
                case "restar":
                    resultado = num1 - num2;
                    break;
                case "multiplicar":
                    resultado = num1 * num2;
                    break;
            }

            // Muestra el resultado
            txtResultado.setText(String.valueOf(resultado));

        } catch (NumberFormatException e) {
            // Error si no son números válidos
            mostrarError("Por favor, introduce números válidos");
        }
    }

    // Abre un archivo con la aplicación predeterminada
    private void abrirArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);

            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            } else {
                mostrarError("El archivo " + nombreArchivo + " no existe en el directorio");
            }
        } catch (IOException e) {
            mostrarError("No se pudo abrir el archivo: " + e.getMessage());
        }
    }

    // Abre una URL en el navegador predeterminado
    private void abrirWeb(String url) {
        try {
            Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            mostrarError("No se pudo abrir la página web: " + e.getMessage());
        }
    }

    // Muestra un cuadro de diálogo de error
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}