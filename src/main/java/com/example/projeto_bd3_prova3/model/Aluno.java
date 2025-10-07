package com.example.projeto_bd3_prova3.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data

public class Aluno {
    @Id
    private String id;
    private String nome;
    private String turma;
    private String matricula;
    private List<Disciplina> disciplinas;
}
