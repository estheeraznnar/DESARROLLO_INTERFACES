package com.example.validation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class HelloController {
    @FXML
    private TextField texto;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button boton;

    ValidationSupport v = new ValidationSupport();

    @FXML
    private void initialize(){
        for (int i = 0; i < 10; i++) {
            combo.getItems().add("Opcion: " + i);
        }
        v.registerValidator(texto, Validator.createEmptyValidator("Hola Caracola"));
        v.registerValidator(combo, Validator.createEmptyValidator("Sacacorchos la mejor palabra"));

        v.registerValidator(checkBox,
                (Control c, Boolean newValue) -> ValidationResult
                        .fromErrorIf(c, "CheckBoxpulsado", !newValue));

    }

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private void setBoton(){
        v.setErrorDecorationEnabled(true);
        if (v.isInvalid()){
            alert.show();
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Faltan campos por rellenar");
        }else {
            alert.show();
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto");
            alert.setContentText("Muy Bien!");
        }

    }

}