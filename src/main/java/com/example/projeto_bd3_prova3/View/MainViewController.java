package com.example.projeto_bd3_prova3.View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class MainViewController {


    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        welcomeLabel.setText("JavaFX integrado com sucesso ao Spring Boot!");
    }
}