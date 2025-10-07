package com.example.projeto_bd3_prova3.service;

import com.example.projeto_bd3_prova3.repository.AlunoRepository;
import com.example.projeto_bd3_prova3.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public void inserirAluno(Aluno aluno) {
        repository.insert(aluno);
    }

    public List<Aluno> buscarAlunos(String turma){
        return repository.findAllByTurma(turma);
    }

    public void excluirAluno(String id) {
        repository.deleteById(id);
        System.out.println("Aluno removido com sucesso");
    }

    public void atualizarNota(String idAluno, String idDisciplina, double novaNota) {
        boolean notaAtualizada = repository.atualizarNotaDisciplina(idAluno, idDisciplina, novaNota);

        if(notaAtualizada) {
            System.out.println("Nota Alterada!");
        } else {
            System.out.println("Não foi possível alterar a nota");
        }
    }

    public Aluno substituirAluno(String idAluno, Aluno novoAluno) {
        novoAluno.setId(idAluno);
        return repository.save(novoAluno);
    }



}

