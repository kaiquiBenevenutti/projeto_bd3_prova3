package com.example.projeto_bd3_prova3.Control;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MainViewController {

    @Autowired
    private AlunoService alunoService;

    @FXML private TextField turmaSearchTextField;
    @FXML private TableView<Aluno> alunosTableView;
    @FXML private TableColumn<Aluno, String> nomeColumn;
    @FXML private TableColumn<Aluno, String> turmaColumn;
    @FXML private TableColumn<Aluno, String> matriculaColumn;

    @FXML private TableColumn<Aluno, String> disciplinasColumn;

    private final ObservableList<Aluno> observableAlunosList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        turmaColumn.setCellValueFactory(new PropertyValueFactory<>("turma"));
        matriculaColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        disciplinasColumn.setCellValueFactory(cellData -> {
            Aluno aluno = cellData.getValue();
            Map<String, Double> disciplinas = aluno.getDisciplinas();

            if (disciplinas == null || disciplinas.isEmpty()) {
                return new SimpleStringProperty("");
            }
            String formattedString = disciplinas.entrySet().stream()
                    .map(entry -> entry.getKey() + ": " + entry.getValue())
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(formattedString);
        });

        alunosTableView.setItems(observableAlunosList);
        atualizarTabela();
    }


    private void atualizarTabela() {
        List<Aluno> todosOsAlunos = alunoService.findAll();
        atualizarTabela(todosOsAlunos);
    }

    private void atualizarTabela(List<Aluno> listaDeAlunos) {
        if (listaDeAlunos == null) listaDeAlunos = Collections.emptyList();
        observableAlunosList.setAll(listaDeAlunos);
        System.out.println("DEBUG: Tabela atualizada com " + listaDeAlunos.size() + " alunos.");
    }


    @FXML
    private void pesquisar() {
        String turmaParaBuscar = turmaSearchTextField.getText().trim();
        List<Aluno> alunosEncontrados = turmaParaBuscar.isEmpty() ? alunoService.findAll() : alunoService.FindAlunos(turmaParaBuscar);
        atualizarTabela(alunosEncontrados);
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
            atualizarTabela();
        } catch (IOException e) { e.printStackTrace(); }
    }
    @FXML
    private void atualizarbutton() {
        Aluno alunoSelecionado = alunosTableView.getSelectionModel().getSelectedItem();
        if (alunoSelecionado == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Atualizar-view.fxml"));
            Parent root = loader.load();
            AlunoUpdateController updateController = loader.getController();
            updateController.setAlunoService(this.alunoService);
            updateController.initData(alunoSelecionado);
            Stage stage = new Stage();
            stage.setTitle("Atualizar Aluno: " + alunoSelecionado.getNome());
            stage.setScene(new Scene(root));
            stage.showAndWait();
            atualizarTabela();
        } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    private void deletar() {
        Aluno alunoSelecionado = alunosTableView.getSelectionModel().getSelectedItem();
        if (alunoSelecionado == null) {
            return;
        }
        try {
            alunoService.excluirAlunoPorMatricula(alunoSelecionado.getMatricula());
            atualizarTabela();
        } catch (Exception e) { e.printStackTrace(); }
    }
}