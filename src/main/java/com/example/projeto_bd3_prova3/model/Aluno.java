package com.example.projeto_bd3_prova3.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "alunos")
public class Aluno {

    @Id
    private ObjectId id;
    private String nome;
    private String turma;
    private String matricula;
    private Map<String, Double> disciplinas;
}
