package com.example.projeto_bd3_prova3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public void SalvarAluno(Aluno aluno) {
        repository.save(aluno);
    }

}
