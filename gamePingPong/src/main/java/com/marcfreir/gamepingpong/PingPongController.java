package com.marcfreir.gamepingpong;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PingPongController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}