package com.example.projeto_bd3_prova3.View;

import com.example.projeto_bd3_prova3.model.Aluno;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.Map;

public class AlunoItemController {

    @FXML
    private Label nomeLabel;

    @FXML
    private Label turmaLabel;

    @FXML
    private Label matriculaLabel;

    @FXML
    private VBox disciplinasBox;


    public void setData(Aluno aluno) {
        nomeLabel.setText(aluno.getNome());
        turmaLabel.setText(aluno.getTurma());
        matriculaLabel.setText(aluno.getMatricula());

        disciplinasBox.getChildren().clear();

        if (aluno.getDisciplinas() != null) {
            for (Map.Entry<String, Double> entry : aluno.getDisciplinas().entrySet()) {
                String textoDisciplina = "• " + entry.getKey() + ": " + entry.getValue();
                Label disciplinaLabel = new Label(textoDisciplina);
                disciplinasBox.getChildren().add(disciplinaLabel);
            }
        }
    }


    @FXML
    private void onLinhaClicked() {
        boolean isVisible = disciplinasBox.isVisible();

        disciplinasBox.setVisible(!isVisible);

        disciplinasBox.setManaged(!isVisible);
    }
}