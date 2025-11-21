package com.example.validation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class HelloController {

    // Definición de los controles de la interfaz JavaFX
    @FXML
    private TextField texto;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button boton;

    // Soporte para la validación de controles (ControlsFX)
    ValidationSupport v = new ValidationSupport();

    @FXML
    private void initialize(){
        // Llena el ComboBox con 10 opciones distintas
        for (int i = 0; i < 10; i++) {
            combo.getItems().add("Opcion: " + i);
        }
        // Valida que el campo de texto no esté vacío; muestra mensaje de error si lo está
        v.registerValidator(texto, Validator.createEmptyValidator("Hola Caracola"));
        // Valida que el combobox tenga un elemento seleccionado
        v.registerValidator(combo, Validator.createEmptyValidator("Sacacorchos la mejor palabra"));
        // Valida que el CheckBox esté marcado; si no lo está, muestra error
        v.registerValidator(checkBox,
                (Control c, Boolean newValue) -> ValidationResult
                        .fromErrorIf(c, "CheckBoxpulsado", !newValue));

    }
    // Crea un Alert para mostrar mensajes de información o error
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    // Metodo que se ejecuta al pulsar el botón
    @FXML
    private void setBoton(){
        v.setErrorDecorationEnabled(true);// Muestra los errores visualmente en los controles
        if (v.isInvalid()){ // Si hay algún error de validación, muestra un Alert de error
            alert.show();
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Faltan campos por rellenar");
        }else {   // Si to do es válido, muestra un Alert de éxito
            alert.show();
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto");
            alert.setContentText("Muy Bien!");
        }

    }

}