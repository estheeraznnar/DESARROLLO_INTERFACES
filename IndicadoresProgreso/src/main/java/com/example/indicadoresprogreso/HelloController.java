package com.example.indicadoresprogreso;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private TextField texto1;

    @FXML
    private TextField texto2;

    @FXML
    private TextField texto3;

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

    @FXML
    private ProgressBar barra;

    @FXML
    private ProgressIndicator circulo;

    @FXML
    private Slider slider;

    @FXML
    public void setBarraAscendiente(){
        if (barra.getProgress()== 1 )return;
        barra.setProgress(barra.getProgress()+0.25);
        texto1.setText(String.valueOf(barra.getProgress()));
    }

    @FXML
    public void setBarraDescendiente(){
        barra.setProgress(barra.getProgress()-0.25);
        texto1.setText(String.valueOf(barra.getProgress()));
    }

    @FXML
    public void setCirculoAscendiente(){
        if (circulo.getProgress()== 1 )return;
        circulo.setProgress(circulo.getProgress()+0.25);
        texto2.setText(String.valueOf(circulo.getProgress()));
    }

    @FXML
    public void setCirculoDescendiente(){
        circulo.setProgress(circulo.getProgress()-0.25);
        texto2.setText(String.valueOf(circulo.getProgress()));
    }

    @FXML
    public void setSliderAscendiente(){
        slider.increment();
        slider.setBlockIncrement(10.0);
        texto3.setText(String.valueOf(slider.getValue()));
    }

    @FXML
    public void setSliderDescendiente(){
        slider.decrement();
        texto3.setText(String.valueOf(slider.getValue()));
    }

    @FXML
    private void setSliderMovil(){
        slider.adjustValue(slider.getValue());
        texto3.setText(String.format("%.2f", slider.getValue()));
    }

}