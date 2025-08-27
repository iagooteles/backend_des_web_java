# Projeto de Desenvolvimento Web em Java

Este é um projeto desenvolvido como parte da disciplina de **Desenvolvimento Web** da faculdade. O sistema utiliza arquitetura de microsserviços com as camadas bem definidas: **DTOs, Repositories, Services, Controllers e Entities**.

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Banco de dados (H2)
- Maven


## 🧱 Padrões e Arquitetura

- **Microsserviços**: Cada domínio do sistema é separado em serviços independentes.
- **DTO**: Usado para transferir dados entre as camadas.
- **Repository**: Interface com o banco de dados usando Spring Data JPA.
- **Service**: Contém a lógica de negócio.
- **Controller**: Camada de entrada da aplicação, responsável por receber e responder requisições.
- **Entity**: Representação das tabelas do banco de dados.

## 📚 Objetivos da Disciplina

- Aplicar conceitos de desenvolvimento web com Java e Spring Boot
- Praticar a construção de APIs RESTful
- Compreender e implementar a arquitetura de microsserviços
- Desenvolver boas práticas com DTOs, serviços e repositórios
