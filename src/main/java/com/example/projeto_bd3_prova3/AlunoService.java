package com.example.projeto_bd3_prova3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public void InserirAluno(Aluno aluno) {

            repository.insert(aluno);

    }

    public List<Aluno> FindAlunos(String turma){
        return repository.findByTurma(turma);
    }
    public void deleteAluno(String id) {
        repository.deleteById(id);
        System.out.println("Aluno removido com sucesso");
    }


}
