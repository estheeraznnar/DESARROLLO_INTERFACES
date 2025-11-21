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

    // Spinner para elegir la hora de la cita (horas)
     @FXML
    private Spinner<Integer> hora;

    // Spinner para elegir la hora de la cita (horas)
     @FXML
    private Spinner<Integer> min;

     @FXML
    private TextArea textArea;

    // Formateador de fechas para dar formato tipo dd/MM/YYYY
     @FXML
     private DateTimeFormatter formatter;

     //configura el formato, los spinners y el área de texto
     @FXML
     private void initialize(){
         formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");// Formato de la fecha
         SpinnerValueFactory<Integer> factory = new SpinnerValueFactory
                 .IntegerSpinnerValueFactory(8, 17, 1);// Horas de 8 a 17
         hora.setValueFactory(factory);
         SpinnerValueFactory<Integer> factory1 = new SpinnerValueFactory
                 .IntegerSpinnerValueFactory(0, 60, 1);// Minutos de 0 a 60
         min.setValueFactory(factory1);
         textArea.setEditable(false);// El usuario no puede editar manualmente el área de texto
     }

    // Metodo que se ejecuta al pulsar el botón para registrar la cita
     @FXML
    private void setTextArea(){
         DecimalFormat formatearNumero = new DecimalFormat("00");// Formatea los números de hora/minuto a dos dígitos
        textArea.appendText("Nombre de paciente: " + nombre.getText());
        textArea.appendText("\nFecha de la cita: " + fecha.getValue().format(formatter));
        textArea.appendText("\nHora de la cita es: " + formatearNumero.format(hora.getValue()) + ":" + formatearNumero.format(min.getValue()));
     }

}