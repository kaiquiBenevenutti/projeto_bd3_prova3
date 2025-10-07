package com.example.projeto_bd3_prova3.repository;

import com.example.projeto_bd3_prova3.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String>, AlunoRepositoryCustom{

    @Query("{'turma' :  ?0 }")
    public List<Aluno> findAllByTurma(String turma);
}

