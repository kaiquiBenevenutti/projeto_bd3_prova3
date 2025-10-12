package com.example.projeto_bd3_prova3;

import com.example.projeto_bd3_prova3.model.Aluno;
import com.example.projeto_bd3_prova3.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
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
        // Rodar o menu em uma thread separada para não travar o Spring Boot
        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            while (true) {
                System.out.println("\nEscolha uma opção: ");
                System.out.println("1 - Listar alunos");
                System.out.println("2 - Excluir aluno");
                System.out.println("3 - Inserir aluno");
                System.out.println("0 - Sair");

                int opcao = s.nextInt();
                s.nextLine();

                if (opcao == 0) {
                    System.out.println("Saindo...");
                    break;
                }

                if (opcao == 1) {
                    System.out.println("Informe a turma:");
                    String turma = s.nextLine();
                    List<Aluno> alunos = service.FindAlunos(turma);
                    alunos.forEach(a -> System.out.println(a.getNome() + " - " + a.getTurma()));
                } else if (opcao == 2) {
                    System.out.println("Informe a matrícula do aluno:");
                    String matricula = s.nextLine();
                    service.excluirAlunoPorMatricula(matricula);
                    System.out.println("Aluno excluído (se existia).");
                } else if (opcao == 3) {
                    Aluno aluno = new Aluno();
                    System.out.println("Informe o nome do aluno:");
                    aluno.setNome(s.nextLine());
                    System.out.println("Informe a matrícula do aluno:");
                    aluno.setMatricula(s.nextLine());
                    System.out.println("Informe a turma do aluno:");
                    aluno.setTurma(s.nextLine());

                    Map<String, Double> disciplinas = new HashMap<>();
                    while (true) {
                        System.out.println("Informe o nome da disciplina ou 'fim':");
                        String nomeDisciplina = s.nextLine();
                        if (nomeDisciplina.equalsIgnoreCase("fim")) break;

                        System.out.println("Informe a nota:");
                        Double nota = s.nextDouble();
                        s.nextLine(); // consumir o ENTER
                        disciplinas.put(nomeDisciplina, nota);
                    }
                    aluno.setDisciplinas(disciplinas);
                    service.inserirAluno(aluno);
                    System.out.println("Aluno inserido com sucesso!");
                } else {
                    System.out.println("Opção inválida.");
                }
            }
        }).start();
    }
}
