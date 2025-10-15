package com.example.projeto_bd3_prova3.repository;

import com.example.projeto_bd3_prova3.model.Aluno;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlunoRepository extends MongoRepository<Aluno, ObjectId>, AlunoRepositoryCustom{

    List<Aluno> findByTurma(String turma);

    void deleteByMatricula(String matricula);

    List<Aluno> findAll();

}

