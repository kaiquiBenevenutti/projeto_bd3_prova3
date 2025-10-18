package com.example.projeto_bd3_prova3.Control;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class AlunoController {

    @FXML private TextField nomeTextField;
    @FXML private TextField matriculaTextField;
    @FXML private TextField turmaTextField;
    @FXML private TextField disciplinasTextField;

    private AlunoService alunoService;


    public void setAlunoService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @FXML
    private void onSalvarClick() {
        String nome = nomeTextField.getText().trim();
        String matricula = matriculaTextField.getText().trim();
        String turma = turmaTextField.getText().trim();
        String disciplinasStr = disciplinasTextField.getText().trim();

        Map<String, Double> disciplinasMap = parseDisciplinas(disciplinasStr);

        Aluno novoAluno = new Aluno(null, nome, turma, matricula, disciplinasMap);

        alunoService.inserirAluno(novoAluno);
        System.out.println("Aluno salvo com sucesso: " + novoAluno.getNome());

        fecharJanela();
    }


    @FXML
    private void CancelarClick() {
        System.out.println("Operação de salvamento cancelada.");
        fecharJanela();
    }


    private void fecharJanela() {
        Stage stage = (Stage) nomeTextField.getScene().getWindow();
        stage.close();
    }


    private Map<String, Double> parseDisciplinas(String disciplinasStr) {
        Map<String, Double> map = new HashMap<>();
        if (disciplinasStr == null || disciplinasStr.isEmpty()) {
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