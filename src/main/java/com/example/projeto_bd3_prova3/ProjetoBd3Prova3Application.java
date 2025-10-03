package com.example.projeto_bd3_prova3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoBd3Prova3Application implements CommandLineRunner {

    @Autowired
    private AlunoService service;

	public static void main(String[] args) {
        SpringApplication.run(ProjetoBd3Prova3Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Pedro");
        aluno.setIdade(90);
        service.SalvarAluno(aluno);
    }

}
