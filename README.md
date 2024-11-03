# EmailSender

## Descrição

O **EmailSender** é um projeto simples desenvolvido com **Spring Boot** que permite o cadastro de usuários e o envio de e-mails de boas-vindas utilizando **RabbitMQ** para mensageria. O projeto também utiliza o banco de dados **H2** para persistência de dados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.5**
- **RabbitMQ**
- **Spring Data JPA**
- **H2 Database**
- **Spring Mail**
- **Lombok**
- **Gradle**

## Funcionalidades

- Cadastro de novos usuários via API REST.
- Envio automático de e-mail de boas-vindas após o cadastro.
- Utilização de RabbitMQ para processamento assíncrono de envio de e-mails.
- Persistência de dados com H2 Database.
- Acesso ao console do H2 para visualização de dados.

## Configuração

### Pré-requisitos

Para executar o projeto, certifique-se de que você possui os seguintes itens instalados:

- **Java 17**: [Download Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **RabbitMQ**: [Download RabbitMQ](https://www.rabbitmq.com/download.html)
- **Gradle**: [Download Gradle](https://gradle.org/install/)

### Variáveis de Ambiente

Antes de executar a aplicação, você deve configurar as seguintes variáveis de ambiente para ocultar informações sensíveis:

- `EMAIL_USERNAME`: Seu endereço de e-mail.
- `EMAIL_PASSWORD`: Sua senha de aplicação de e-mail.

Para configurar as variáveis de ambiente no seu sistema, siga estas instruções:

#### Windows

1. Abra o menu Iniciar e procure por "Configurações do sistema".
2. Clique em "Variáveis de ambiente".
3. Adicione as variáveis `EMAIL_USERNAME` e `EMAIL_PASSWORD` com os respectivos valores.

#### macOS e Linux

1. Abra o terminal.
2. Adicione as variáveis ao arquivo `.bash_profile`, `.bashrc` ou `.zshrc`:

   ```bash
   export EMAIL_USERNAME="seu_email@gmail.com"
   export EMAIL_PASSWORD="sua_senha"
