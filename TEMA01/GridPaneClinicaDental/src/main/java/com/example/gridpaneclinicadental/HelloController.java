package com.example.gridpaneclinicadental;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class HelloController {
    @FXML
    private TextField nombre;

    @FXML
    private Button registrar;

     @FXML
    private DatePicker fecha;

     @FXML
    private Spinner<Integer> hora;

     @FXML
    private Spinner<Integer> min;

     @FXML
    private TextArea textArea;

     @FXML
     private DateTimeFormatter formatter;

     @FXML
     private void initialize(){
         formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
         SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 17, 1);
         hora.setValueFactory(factory);
         SpinnerValueFactory<Integer> factory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 1);
         min.setValueFactory(factory1);
         textArea.setEditable(false);
     }

     @FXML
    private void setTextArea(){
         DecimalFormat formatearNumero = new DecimalFormat("00");
        textArea.appendText("Nombre de paciente: " + nombre.getText());
        textArea.appendText("\nFecha de la cita: " + fecha.getValue().format(formatter));
        textArea.appendText("\nHora de la cita es: " + formatearNumero.format(hora.getValue()) + ":" + formatearNumero.format(min.getValue()));
     }

}