package com.example.projeto_bd3_prova3;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AlunoRepository extends MongoRepository<Aluno, String> {

    List<Aluno> findByTurma(String turma);

}
