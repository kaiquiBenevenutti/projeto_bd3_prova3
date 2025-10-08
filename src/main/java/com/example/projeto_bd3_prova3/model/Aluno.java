package com.example.projeto_bd3_prova3.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Getter
@Setter

public class Aluno {
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Id
    private String _id;
    private String nome;
    private String turma;
    private String matricula;
    private List<Disciplina> disciplinas;
}
