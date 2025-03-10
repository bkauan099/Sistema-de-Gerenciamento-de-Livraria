# Sistema de Gerênciamento de Livraria

Esse projeto foi desenvolvido com o objetivo de gerenciar uma livraria,
permitindo cadastro de usuários e livros, além de funções para a consulta,
alteração, exclusão e reserva de livros. O projeto também utiliza-se do JDBC
para realizar uma comunicação com um banco de dados, permitindo a
execução de comandos SQL, necessárias para a realização precisa das
funções do projeto, além disto, o projeto apresenta uma interface gráfica
indutiva utilizando-se da tecnologia Swing para facilitar a navegação entre as
funções do projeto.

## 📌 Funcionalidades
- ✅ Cadastro de livros  
- ✅ Cadastro de usuários
- ✅ Realização de Reservas  
- 🔍 Consulta de livros  
- ❌ Remoção de livros  
- ❌ Remoção de usuários
- ❌ Remoção de reservas 


## 🛠 Tecnologias Utilizadas
- Java  
- MySQL  
- JDBC  
- Swing
- Docker

## ⚙️ Instalação
    1. Clone o repositório do projeto:  
   ```sh
    2. Instale o MySQL e crie um banco de dados com o nome libra_tech.

    3. Configure as propriedades de conexão ao banco de dados no arquivo: src/Livraria/bancoDados/ConexaoBD.java

    4. Crie as tabelas que estão no arquivo modelo.sql

    5. Compile e execute o projeto.

    OBS: Nesse projeto foi utilizada a biblioteca: Mysql-connector-j-9-2.0.jar

    Esse arquivo tem um driver JDBC (Java Database Connectivity) fornecido pela
    Oracle para permitir que aplicações Java interajam com um banco de dados
    MySQL. Ele serve como um intermediário entre o código Java e o banco de
    dados, traduzindo as instruções SQL enviadas pela aplicação para comandos
    compreendidos pelo MySQL.

    Banco de Dados e Estrutura SQL:

    Dentro do projeto o arquivo modelo.sql contém os comandos SQL responsáveis
    pela criação das tabelas e estruturação do banco de dados utilizado pelo
    sistema. Esse arquivo garante que, ao configurar o projeto em um novo ambiente
    ou servidor, a base de dados seja criada corretamente sem necessidade de
    intervenção manual.
```

🚀 Uso e exemplos:


![Captura de tela 2025-03-02 095140](https://github.com/user-attachments/assets/ea7ee7d8-4faa-474a-920e-7fa80d1f7fd8)

![Captura de tela 2025-03-02 095152](https://github.com/user-attachments/assets/d1509f55-482f-4cbf-ad24-b6ae96c4c89e)

![Captura de tela 2025-03-02 095159](https://github.com/user-attachments/assets/19a60fde-24e5-4d3a-a10e-a5b951d30aeb)

![Captura de tela 2025-03-02 095209](https://github.com/user-attachments/assets/b5653a60-dbe8-415f-bfdd-fdc888581493)






