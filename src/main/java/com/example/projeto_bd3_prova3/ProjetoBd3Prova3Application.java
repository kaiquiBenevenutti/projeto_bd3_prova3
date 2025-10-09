package com.example.projeto_bd3_prova3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetoBd3Prova3Application implements CommandLineRunner {

    @Autowired
    private AlunoService service;

	public static void main(String[] args) {
        SpringApplication.run(ProjetoBd3Prova3Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        List<Aluno> alunos = new ArrayList<>();

        alunos = service.FindAlunos("ADS-2023");

        alunos.forEach(aluno -> System.out.println(aluno.getNome() + aluno.getTurma()));
    }

}
