package com.example.projeto_bd3_prova3.View;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class MainViewController {

    @Autowired
    private AlunoService alunoService;

    @FXML
    private VBox alunosVBox;

    @FXML
    private TextField turmaSearchTextField;

    @FXML
    public void initialize() {
        atualizarListaDeAlunos();
    }

    private void atualizarListaDeAlunos() {
        List<Aluno> todosOsAlunos = alunoService.findAll();
        atualizarListaDeAlunos(todosOsAlunos);
    }

    private void atualizarListaDeAlunos(List<Aluno> listaDeAlunos) {
        alunosVBox.getChildren().clear();

        if (listaDeAlunos == null) {
            listaDeAlunos = Collections.emptyList();
        }


        for (Aluno aluno : listaDeAlunos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/aluno_item_view.fxml"));
                Node linhaDoAluno = loader.load();

                AlunoItemController controllerDaLinha = loader.getController();
                controllerDaLinha.setData(aluno);

                alunosVBox.getChildren().add(linhaDoAluno);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void pesquisar() {
        String turmaParaBuscar = turmaSearchTextField.getText().trim();
        List<Aluno> alunosEncontrados;

        if (turmaParaBuscar.isEmpty()) {
            atualizarListaDeAlunos();
        } else {
            alunosEncontrados = alunoService.FindAlunos(turmaParaBuscar);
            atualizarListaDeAlunos(alunosEncontrados);
        }
    }

    @FXML
    private void calcularMedia() {
        // Chama o serviço
        Map<String, Double> medias = alunoService.getMediaNotasPorDisciplina();

        // Exibe no console
        System.out.println("=== Médias por Disciplina ===");
        medias.forEach((disciplina, media) ->
                System.out.println(disciplina + ": " + media)
        );

        // Opcional: exibir na interface
        alunosVBox.getChildren().clear();
        medias.forEach((disciplina, media) -> {
            Label lbl = new Label(disciplina + ": " + String.format("%.2f", media));
            lbl.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            alunosVBox.getChildren().add(lbl);
        });
    }


    // Métodos de ação para os botões (ainda não implementados)
    @FXML
    private void adicionar() {
        System.out.println("Botão Adicionar Clicado!");
    }

    @FXML
    private void atualizar() {
        System.out.println("Botão Atualizar Clicado!");
    }

    @FXML
    private void deletar() {
        System.out.println("Botão Deletar Clicado!");
    }
}