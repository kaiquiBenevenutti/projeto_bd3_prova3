package com.example.projeto_bd3_prova3;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ProjetoBd3Prova3Application implements CommandLineRunner {

    @Autowired
    private AlunoService service;

    public static void main(String[] args) {
        SpringApplication.run(ProjetoBd3Prova3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        int opcao=0;
        Aluno alunos = new Aluno();

        Scanner s = new Scanner(System.in);
        opcao=s.nextInt();//fiz para testar
        if (opcao == 1){//metodo find
        List<Aluno> Alunos = new ArrayList<>();
            Alunos = service.FindAlunos("ADS-2023");
            Alunos.forEach(aluno -> System.out.println(aluno.getNome() + aluno.getTurma()));
        }else  if (opcao == 2){//delete
            System.out.println("Informe a matricula do aluno");
            String matricula = s.next();
            service.excluirAlunoPorMatricula(matricula);
        }else if (opcao == 3){//insert
            System.out.println("Informe o nome do aluno");
            alunos.setNome(s.next());
            System.out.println("Informe a matricula do aluno");
            alunos.setMatricula(s.next());
            System.out.println("Informe a turma do aluno");
            alunos.setTurma(s.next());

            Map<String, Double> disciplinas = new HashMap<>();

            while (true) {
                System.out.println("Informe o nome da disciplina ou digite 'fim' para terminar ");
                String nomeDisciplina = s.next();
                if (nomeDisciplina.equalsIgnoreCase("fim")) {
                    break;
                }
                System.out.println("Informe a nota para " + nomeDisciplina + ":");
                Double nota = s.nextDouble();
                disciplinas.put(nomeDisciplina, nota);
            }
            alunos.setDisciplinas(disciplinas);
            service.inserirAluno(alunos);

        }
    }

}
