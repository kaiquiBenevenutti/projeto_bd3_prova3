package com.example.projeto_bd3_prova3.View;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class MainViewController {

    @Autowired
    private AlunoService alunoService;

    @FXML
    private VBox alunosVBox;

    @FXML
    private TextField turmaSearchTextField;

    private Aluno alunoSelecionado = null;
    private AlunoItemController controllerSelecionado = null;

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
        alunoSelecionado = null;
        controllerSelecionado = null;

        if (listaDeAlunos == null) {
            listaDeAlunos = Collections.emptyList();
        }

        System.out.println("DEBUG: Exibindo " + listaDeAlunos.size() + " alunos na tela.");

        for (Aluno aluno : listaDeAlunos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/aluno_item_view.fxml"));
                Node linhaDoAluno = loader.load();
                AlunoItemController controllerDaLinha = loader.getController();

                controllerDaLinha.setData(aluno, (controllerClicado) -> {
                    if (controllerSelecionado != null) {
                        controllerSelecionado.setSelecionado(false);
                    }
                    controllerSelecionado = controllerClicado;
                    alunoSelecionado = controllerClicado.getAluno();
                    controllerSelecionado.setSelecionado(true);
                    System.out.println("Aluno selecionado: " + alunoSelecionado.getNome());
                });

                alunosVBox.getChildren().add(linhaDoAluno);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void pesquisar() {
        String turmaParaBuscar = turmaSearchTextField.getText().trim();
        if (turmaParaBuscar.isEmpty()) {
            atualizarListaDeAlunos();
        } else {
            List<Aluno> alunosEncontrados = alunoService.FindAlunos(turmaParaBuscar);
            atualizarListaDeAlunos(alunosEncontrados);
        }
    }


    @FXML
    private void adicionarbutton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/aluno-view.fxml"));
            Parent root = loader.load();
            AlunoController formController = loader.getController();
            formController.setAlunoService(this.alunoService);
            Stage stage = new Stage();
            stage.setTitle("Adicionar Novo Aluno");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            atualizarListaDeAlunos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void atualizar() {
        System.out.println("Botão Atualizar Clicado!");
    }


    @FXML
    private void deletar() {
        if (alunoSelecionado == null) {
            System.out.println("Nenhum aluno selecionado. Ação de deletar ignorada.");
            return;
        }

        try {
            alunoService.excluirAlunoPorMatricula(alunoSelecionado.getMatricula());
            System.out.println("Aluno deletado com sucesso: " + alunoSelecionado.getNome());
            atualizarListaDeAlunos();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao tentar deletar o aluno.");
            e.printStackTrace();
        }
    }
}