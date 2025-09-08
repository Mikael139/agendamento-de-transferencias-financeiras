# Objetivo do Projeto
O objetivo principal é criar um sistema que permite aos usuários agendar transferências financeiras, aplicando regras de negócio específicas para o cálculo de taxas. O sistema também oferece um endpoint para visualizar o extrato de todos os agendamentos realizados.

# As regras de negócio para o cálculo das taxas são as seguintes:

- Até 0 dias de diferença: Taxa de R$ 3,00 + 2,5% do valor da transferência.
- De 1 a 10 dias de diferença: Taxa fixa de R$ 12,00.
- De 11 a 20 dias de diferença: Taxa de 8,2% sobre o valor da transferência.
- De 21 a 30 dias de diferença: Taxa de 6,9% sobre o valor da transferência.
- De 31 a 40 dias de diferença: Taxa de 4,7% sobre o valor da transferência.
- De 41 a 50 dias de diferença: Taxa de 1,7% sobre o valor da transferência.

Obs: Mais de 50 dias de diferença: A transferência não é permitida.

# Decisões Arquiteturais e Tecnologias
A aplicação segue o padrão de arquitetura em camadas, com uma clara separação de responsabilidades. Isso facilita a manutenção, a escalabilidade e a testabilidade do código.

- Controlador (Controller): Recebe as requisições HTTP e delega as chamadas para a camada de serviço.

- Serviço (Service): Contém a lógica de negócio e orquestra as operações entre as outras camadas, como o cálculo da taxa e a persistência dos dados.

- Repositório (Repository): Interface para comunicação com o banco de dados.

# Tecnologias e Ferramentas
- Linguagem: Java 11
- Framework: Spring Boot 2.7.18
- Gerenciador de Dependências: Maven
- Persistência: Spring Data JPA
- Banco de Dados: H2 (banco de dados em memória)
- Lombok (reduz a verbosidade do código, gerando getters, setters, etc.)

# Como Rodar o Projeto?
Pré-requisitos:

Java Development Kit (JDK) 11 ou superior.

Maven 3.6 ou superior.

1. Clonar o Repositório
```bash
git clone https://github.com/Mikael139/agendamento-de-transferencias-financeiras.git
```
```bash
cd agendamento-transferencias
```

2. Executar a Aplicação

Você pode usar o Maven para compilar e executar o projeto com o comando abaixo. O Spring Boot automaticamente inicia um servidor web embutido (Tomcat) na porta 8080.
```bash
mvn spring-boot:run
```

Se tudo estiver correto, a aplicação estará rodando em:
```bash
http://localhost:8080
```

# Front-End (Vue.js)
O desafio também inclui o desenvolvimento de um front-end simples para interagir com a API. A aplicação foi construída com Vue.js para demonstrar a integração entre as camadas de back-end e front-end.

[Link do repositório](https://github.com/Mikael139/agendamento-de-transferencias-financeiras-front)

<img width="1920" height="917" alt="image" src="https://github.com/user-attachments/assets/91637964-a4cd-4b54-99ab-75d3770cdeb9" />

A aplicação Vue.js tem dois componentes principais:

Formulário de Agendamento: Um formulário que permite ao usuário inserir os dados da transferência e enviar uma requisição POST para a API.

Extrato de Agendamentos: Uma lista que faz uma requisição GET para a API e exibe todos os agendamentos registrados no banco de dados.

Esta aplicação front-end consome a API REST criada, validando o funcionamento dos endpoints e fornecendo uma interface de usuário funcional para o sistema.

# Como Testar a API?
Você pode usar o Postman ou qualquer cliente REST para interagir com a API.

Endpoint: /api/transferencias/agendar
```bash
URL: http://localhost:8080/api/transferencias/agendar
```

Método: POST

Corpo da requisição (JSON):
```bash
{
  "contaOrigem": "1111111111",
  "contaDestino": "2222222222",
  "valor": 1000.00,
  "dataTransferencia": "2025-10-10"
}
```

Endpoint: /api/transferencias/extrato
```bash
URL: http://localhost:8080/api/transferencias/extrato
```

Método: GET

Corpo da requisição: Não é necessário.

Resposta: Uma lista JSON com todos os agendamentos.

# Acessando o Banco de Dados H2
Como o projeto usa o H2 como banco de dados em memória, você pode acessá-lo via um console web para verificar a persistência dos dados.

1. Ativar o Console
Verifique se as seguintes linhas estão no arquivo src/main/resources/application.properties:

```bash
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

2. Acessar o Console
Com a aplicação rodando, acesse a URL no seu navegador:

```bash
http://localhost:8080/h2-console
```

Use as seguintes informações para se conectar:

```bash
JDBC URL: jdbc:h2:mem:testdb

User Name: seuusuario

Password: suasenha
```

3. Comandos Básicos
Dentro do console H2, você pode executar comandos SQL para inspecionar os dados.

Para ver todos os agendamentos salvos:

```bash
SELECT * FROM TRANSFERENCIA;
```

Para contar o número de registros:
```bash
SELECT COUNT(*) FROM TRANSFERENCIA;
```
