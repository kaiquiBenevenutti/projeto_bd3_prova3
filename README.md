# Alunos — Spring Boot + JavaFX + MongoDB

Aplicação desktop (JavaFX) integrada ao Spring Boot e MongoDB para gerenciamento de alunos (turmas, matrículas e notas por disciplina).

## Visão geral
Este projeto combina Spring Boot (injeção de dependências e acesso a dados via Spring Data MongoDB) com JavaFX (interface gráfica desktop). O contexto Spring é inicializado dentro da aplicação JavaFX, permitindo utilizar serviços e repositórios diretamente nos controladores da UI.


## Tecnologias
- Java 21
- Spring Boot 3.5.x
- Spring Data MongoDB
- JavaFX 21
- Maven
- Lombok


## Pré-requisitos
- JDK 21 instalado e configurado no PATH
- Maven 3.9+ instalado
- MongoDB 7+ em execução (padrão: localhost:27017)


## Configuração
Configuração das credencias do banco

spring.application.name=Alunos
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=projeto_bd3_prova3


## Modelo de dados
Coleção: alunos


Campos (classe Aluno):
- id: ObjectId
- nome: String
- turma: String
- matricula: String
- disciplinas: Map<String, Double> (chave = nome da disciplina, valor = nota)

Exemplo de documento:

{
"_id": "6521f7e9c0a4f01ab2c3d456",
"nome": "Maria Silva",
"turma": "3A",
"matricula": "20250001",
"disciplinas": {
"Matemática": 8.5,
"Português": 9.0
}
}


## Operações principais
Centralizadas em AlunoService e repositórios:
- inserirAluno - insere um novo aluno
- FindAlunos - lista alunos por turma
- excluirAlunoPorMatricula - remove aluno pela matrícula
- substituirAluno - Substitui por completo um aluno existente


## Interface gráfica (JavaFX)
- Cena principal - main-view.fxml
- Tela de atualizacao - Atualizar-view.fxml
- tela de insercao - Aluno-view.fxml 

