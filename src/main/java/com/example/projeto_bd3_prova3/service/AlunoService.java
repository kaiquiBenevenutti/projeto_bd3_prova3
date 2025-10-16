package com.example.projeto_bd3_prova3.service;

import com.example.projeto_bd3_prova3.repository.AlunoRepository;
import com.example.projeto_bd3_prova3.model.Aluno;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public void inserirAluno(Aluno aluno) {
        repository.insert(aluno);
    }

    public List<Aluno> FindAlunos(String turma){
        return repository.findByTurma(turma);
    }

    public void excluirAlunoPorMatricula(String matricula) {
        repository.deleteByMatricula(matricula);
        System.out.println("Aluno removido com sucesso");
    }

    public void atualizarNota(String idAluno, String disciplina, double novaNota) {
        ObjectId objectId = new ObjectId(idAluno);

        boolean notaAtualizada = repository.atualizarNotaDisciplina(objectId, disciplina, novaNota);

        if(notaAtualizada) {
            System.out.println("Nota Alterada!");
        } else {
            System.out.println("Não foi possível alterar a nota");
        }
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

   public Aluno substituirAluno(String idAluno, Aluno novoAluno) {
        ObjectId objectId = new ObjectId(idAluno);
        novoAluno.setId(objectId);
        return repository.save(novoAluno);
    }

    public Map<String, Double> getMediaNotasPorDisciplina() {
        return repository.calcularMediaPorDisciplina();
    }


}





