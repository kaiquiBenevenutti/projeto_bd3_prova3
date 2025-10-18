package com.example.projeto_bd3_prova3.Control;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AlunoUpdateController {

    @FXML private TextField nomeTextField;
    @FXML private TextField matriculaTextField;
    @FXML private TextField turmaTextField;
    @FXML private TextField disciplinasTextField;

    private AlunoService alunoService;
    private Aluno alunoParaAtualizar;

    public void setAlunoService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    public void initData(Aluno aluno) {
        this.alunoParaAtualizar = aluno;
        nomeTextField.setText(aluno.getNome());
        matriculaTextField.setText(aluno.getMatricula());
        turmaTextField.setText(aluno.getTurma());
        disciplinasTextField.setText(mapToString(aluno.getDisciplinas()));
    }

    @FXML
    private void onAtualizarClick() {

        alunoParaAtualizar.setNome(nomeTextField.getText().trim());
        alunoParaAtualizar.setMatricula(matriculaTextField.getText().trim());
        alunoParaAtualizar.setTurma(turmaTextField.getText().trim());
        alunoParaAtualizar.setDisciplinas(parseDisciplinas(disciplinasTextField.getText()));

        alunoService.atualizarAluno(alunoParaAtualizar);
        System.out.println("Aluno atualizado com sucesso: " + alunoParaAtualizar.getNome());
        fecharJanela();
    }

    @FXML
    private void onCancelarClick() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) nomeTextField.getScene().getWindow();
        stage.close();
    }

    private String mapToString(Map<String, Double> map) {
        if (map == null || map.isEmpty()) return "";
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    private Map<String, Double> parseDisciplinas(String disciplinasStr) {
        Map<String, Double> map = new HashMap<>();
        if (disciplinasStr == null || disciplinasStr.trim().isEmpty()) {
            return map;
        }
        String[] pares = disciplinasStr.split(",");
        for (String par : pares) {
            String[] chaveValor = par.split("=");
            if (chaveValor.length == 2) {
                try {
                    String nome = chaveValor[0].trim();
                    Double nota = Double.parseDouble(chaveValor[1].trim());
                    map.put(nome, nota);
                } catch (NumberFormatException e) {
                    System.err.println("Ignorando par de disciplina mal formatado: " + par);
                }
            }
        }
        return map;
    }
}