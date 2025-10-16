package com.example.projeto_bd3_prova3.repository;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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

    @Override
    public Map<String, Double> calcularMediaPorDisciplina() {

        Aggregation aggregation = newAggregation(
                context -> new Document("$replaceWith",
                        new Document("disciplinas", new Document("$objectToArray", "$disciplinas"))
                ),
                unwind("disciplinas"),
                group("$disciplinas.k").avg("$disciplinas.v").as("media")
        );

        AggregationResults<Document> results =
                mongoTemplate.aggregate(aggregation, "aluno", Document.class);

        return results.getMappedResults()
                .stream()
                .collect(Collectors.toMap(
                        doc -> doc.getString("_id"), // nome da disciplina
                        doc -> doc.getDouble("media") // média
                ));
    }

}
