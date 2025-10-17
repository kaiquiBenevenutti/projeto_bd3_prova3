    package com.example.projeto_bd3_prova3.View;

    import com.example.projeto_bd3_prova3.model.Aluno;
    import javafx.fxml.FXML;
    import javafx.scene.control.Label;
    import javafx.scene.layout.VBox;
    import java.util.Map;
    import java.util.function.Consumer; // ✅ Adicione este import

    public class AlunoItemController {

        @FXML private VBox itemContainer;
        @FXML private Label nomeLabel;
        @FXML private Label turmaLabel;
        @FXML private Label matriculaLabel;
        @FXML private VBox disciplinasBox;

        private Aluno aluno;
        private Consumer<AlunoItemController> onItemSelected;


        public void setData(Aluno aluno, Consumer<AlunoItemController> onItemSelected) {
            this.aluno = aluno;
            this.onItemSelected = onItemSelected;

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
            if (onItemSelected != null) {
                onItemSelected.accept(this);
            }

            boolean isVisible = disciplinasBox.isVisible();
            disciplinasBox.setVisible(!isVisible);
            disciplinasBox.setManaged(!isVisible);
        }


        public void setSelecionado(boolean selecionado) {
            if (selecionado) {
                itemContainer.setStyle("-fx-background-color: #d1e7ff; -fx-background-radius: 8px;");
            } else {
                itemContainer.setStyle("");
            }
        }

        public Aluno getAluno() {
            return this.aluno;
        }
    }