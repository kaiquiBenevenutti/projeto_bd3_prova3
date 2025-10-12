package com.example.projeto_bd3_prova3.repository;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@AllArgsConstructor
public class AlunoRepositoryCustomImpl implements AlunoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public boolean atualizarNotaDisciplina(ObjectId idAluno, String disciplina, double novaNota) {
        Query query = new Query(Criteria.where("_id").is(idAluno));

        Update update = new Update().set("disciplinas." + disciplina, novaNota);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Aluno.class);

        return result.getModifiedCount() > 0;
    }
}
