package com.example.projeto_bd3_prova3.repository;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

interface AlunoRepositoryCustom {
    boolean atualizarNotaDisciplina(ObjectId idAluno, String idDisciplina, double novaNota);

    Map<String, Double> calcularMediaPorDisciplina();
}
