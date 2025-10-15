package com.example.projeto_bd3_prova3.View;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class MainViewController {

    @Autowired
    private AlunoService alunoService;

    @FXML
    private VBox alunosVBox;


    @FXML
    public void initialize() {
        carregarAlunosNaTela();
    }

    private void carregarAlunosNaTela() {
        alunosVBox.getChildren().clear();

        List<Aluno> listaDeAlunos = alunoService.findAll();
        System.out.println("DEBUG: Encontrados " + listaDeAlunos.size() + " alunos para exibir.");

        for (Aluno aluno : listaDeAlunos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/aluno_item_view.fxml"));
                Node linhaDoAluno = loader.load();

                AlunoItemController controllerDaLinha = loader.getController();
                controllerDaLinha.setData(aluno);

                alunosVBox.getChildren().add(linhaDoAluno);

            } catch (IOException e) {
                System.err.println("Erro ao carregar lista");
                e.printStackTrace();
            }
        }
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