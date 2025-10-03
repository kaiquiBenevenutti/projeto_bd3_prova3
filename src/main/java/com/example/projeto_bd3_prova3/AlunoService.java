package com.example.projeto_bd3_prova3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public void InserAluno(Aluno aluno) {

        if (aluno.getId() != repository.findById(aluno.getId()).get().getId()) {
            repository.insert(aluno);
        } else {
            System.out.println("Aluno já existente");
        }
    }

    public List<Aluno> FindAlunos(String turma){
        var retorno = repository.findAllByTurma(turma);
        return retorno;
    }
}
