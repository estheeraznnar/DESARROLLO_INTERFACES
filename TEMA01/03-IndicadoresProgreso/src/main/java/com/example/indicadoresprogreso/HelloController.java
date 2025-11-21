package com.example.indicadoresprogreso;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    // Campos de texto donde se mostrarán o introducirán valores
    @FXML
    private TextField texto1;

    @FXML
    private TextField texto2;

    @FXML
    private TextField texto3;

    // Botones para activar diferentes acciones en la interfaz
    @FXML
    private Button boton1;

    @FXML
    private Button boton2;

    @FXML
    private Button boton3;

    @FXML
    private Button boton4;

    @FXML
    private Button boton5;

    @FXML
    private Button boton6;

    // Indicadores visuales de progreso
    @FXML
    private ProgressBar barra;// Barra de progreso horizontal

    @FXML
    private ProgressIndicator circulo;// Indicador circular de progreso

    // Controles para seleccionar valores numéricos
    @FXML
    private Slider slider;// Control deslizante

    @FXML
    private Spinner<Integer> spiner;// Selector giratorio de enteros

    @FXML
    private void initialize(){
        // Crea y asigna un rango de valores al Spinner, con valor inicial de 50
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(0, 100, 50);
        spiner.setValueFactory(factory);
    }

    @FXML
    public void setBarraAscendiente(){
        // Incrementa la barra de progreso en 0.25, hasta un máximo de 1. Actualiza texto1.
        if (barra.getProgress()== 1 )return;
        barra.setProgress(barra.getProgress()+0.25);
        texto1.setText(String.valueOf(barra.getProgress()));
    }

    // Decrementa la barra de progreso en 0.25. Actualiza texto1.
    @FXML
    public void setBarraDescendiente(){
        barra.setProgress(barra.getProgress()-0.25);
        texto1.setText(String.valueOf(barra.getProgress()));
    }

    // Incrementa el indicador circular en 0.25, hasta un máximo de 1. Actualiza texto2.
    @FXML
    public void setCirculoAscendiente(){
        if (circulo.getProgress()== 1 )return;
        circulo.setProgress(circulo.getProgress()+0.25);
        texto2.setText(String.valueOf(circulo.getProgress()));
    }

    // Decrementa el indicador circular en 0.25. Actualiza texto2.
    @FXML
    public void setCirculoDescendiente(){
        circulo.setProgress(circulo.getProgress()-0.25);
        texto2.setText(String.valueOf(circulo.getProgress()));
    }

    // Incrementa el slider y muestra el valor en texto3
    @FXML
    public void setSliderAscendiente(){
        slider.increment();
        slider.setBlockIncrement(10.0);
        texto3.setText(String.valueOf(slider.getValue()));
    }

    // Decrementa el slider y muestra el valor en texto3
    @FXML
    public void setSliderDescendiente(){
        slider.decrement();
        texto3.setText(String.valueOf(slider.getValue()));
    }

    // Actualiza el valor mostrado en texto3 según donde esté el slider, con dos decimales
    //se pone en On KeyPressed y en On Key Released, onMouseDragged, onMouseClicked
    @FXML
    private void setSliderMovil(){
        slider.adjustValue(slider.getValue());
        texto3.setText(String.format("%.2f", slider.getValue()));
    }

}