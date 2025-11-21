package org.example.reproductormusica;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class HelloController {
    private MediaPlayer mediaPlayer;

    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button stopButton;
    @FXML
    private ComboBox<String> comboCanciones;

    @FXML
    public void initialize() {
        // Supón que tienes archivos mp3 en la raíz del proyecto
        comboCanciones.getItems().addAll("cancion1.mp3", "cancion2.mp3", "cancion3.mp3");
        comboCanciones.getSelectionModel().selectFirst();
        cargarCancionSeleccionada();
        comboCanciones.setOnAction(e -> cargarCancionSeleccionada());
    }

    private void cargarCancionSeleccionada() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        String cancion = comboCanciones.getValue();
        String path = new File(cancion).toURI().toString();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
    }

    @FXML
    private void playMusica() {
        if (mediaPlayer != null) mediaPlayer.play();
    }

    @FXML
    private void pauseMusica() {
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    @FXML
    private void stopMusica() {
        if (mediaPlayer != null) mediaPlayer.stop();
    }
}