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
- Opcional: Docker para subir o MongoDB rapidamente


## Configuração
As principais configurações estão em src/main/resources/application.properties:


spring.application.name=Alunos
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=projeto_bd3_prova3


Você pode sobrescrever essas propriedades via variáveis de ambiente do Spring (por exemplo, SPRING_DATA_MONGODB_HOST, SPRING_DATA_MONGODB_PORT, SPRING_DATA_MONGODB_DATABASE).


## Como executar
1) Subir o MongoDB (via Docker, opcional):


docker run -d --name mongo -p 27017:27017 mongo:7


2) Executar a interface JavaFX (recomendado para usar o app):


mvn -q javafx:run


O plugin javafx-maven-plugin já está configurado no pom.xml com a mainClass com.example.projeto_bd3_prova3.View.JavaFxApplication.


3) Executar apenas o contexto Spring Boot (sem UI):


mvn spring-boot:run


4) Gerar build:


mvn clean package


Observação: o JAR gerado pode não embutir os módulos JavaFX. Para distribuição desktop, recomenda-se usar o objetivo javafx:run durante o desenvolvimento, e para empacotar use ferramentas como jlink/jpackage conforme necessidade.


## Estrutura do projeto
- src/main/java/com/example/projeto_bd3_prova3/ProjetoBd3Prova3Application.java — classe principal Spring Boot (contexto da aplicação)
- src/main/java/com/example/projeto_bd3_prova3/View/JavaFxApplication.java — inicializa o Spring e carrega a cena JavaFX (FXML)
- src/main/java/com/example/projeto_bd3_prova3/view controllers — MainViewController, AlunoItemController (controladores da UI)
- src/main/java/com/example/projeto_bd3_prova3/model/Aluno.java — modelo de domínio mapeado para a coleção alunos
- src/main/java/com/example/projeto_bd3_prova3/repository — AlunoRepository, AlunoRepositoryCustom, AlunoRepositoryCustomImpl
- src/main/java/com/example/projeto_bd3_prova3/service/AlunoService.java — regras de negócio para Aluno
- src/main/resources/main-view.fxml e aluno_item_view.fxml — telas JavaFX
- src/main/resources/application.properties — propriedades da aplicação e conexão MongoDB
- pom.xml — dependências e plugins (Spring Boot, JavaFX, Maven Compiler, etc.)


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
- inserirAluno(Aluno aluno) — insere um novo aluno
- FindAlunos(String turma) — lista alunos por turma
- excluirAlunoPorMatricula(String matricula) — remove aluno pela matrícula
- substituirAluno(String idAluno, Aluno novoAluno) — substitui por completo um aluno existente
- atualizarNota(String idAluno, String disciplina, double novaNota) — atualiza a nota de uma disciplina específica (update em disciplinas.<disciplina>)


A implementação customizada de atualização de notas usa MongoTemplate para aplicar um update atômico no documento do aluno.


## Interface gráfica (JavaFX)
- Cena principal carregada de main-view.fxml
- Itens de aluno definidos em aluno_item_view.fxml
- Título da janela: “Minha Aplicação”
- Scene padrão: 800x600


Observação: os controladores (MainViewController, AlunoItemController) são gerenciados pelo Spring via setControllerFactory, permitindo injeção de dependências diretamente na camada de UI.

