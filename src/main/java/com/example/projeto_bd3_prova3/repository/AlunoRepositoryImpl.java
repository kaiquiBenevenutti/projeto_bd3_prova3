package com.example.projeto_bd3_prova3.repository;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@AllArgsConstructor
public class AlunoRepositoryImpl implements AlunoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public boolean atualizarNotaDisciplina(String idAluno, String idDisciplina, double novaNota) {
        Query query = new Query(
                Criteria.where("_id").is(idAluno)
                        .and("disciplinas.id").is(idDisciplina)
        );

        Update update = new Update().set("disciplinas.$.nota", novaNota);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Aluno.class);

        return result.getModifiedCount() > 0;
    }
}
