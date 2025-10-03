package com.example.projeto_bd3_prova3;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Aluno {
    @Id
    private String id;
    private String nome;
    private int idade;
}
