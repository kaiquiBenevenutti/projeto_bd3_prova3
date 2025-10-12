package com.example.projeto_bd3_prova3.repository;

import org.bson.types.ObjectId;

interface AlunoRepositoryCustom {
    boolean atualizarNotaDisciplina(ObjectId idAluno, String idDisciplina, double novaNota);
}
