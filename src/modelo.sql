-- Active: 1737557740149@@localhost@3307@mysqlDatabase
-- criar banco de dados para o LibraTech
CREATE DATABASE libra_tech;

USE libra_tech;
-- criar tabela de livros
CREATE TABLE IF NOT EXISTS livros (
    id_livro INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL
);

-- criar tabelas de usuarios
CREATE TABLE IF NOT EXISTS usuarios(
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL
);

-- criar tabela de reservas que relaciona usuarios e livros
CREATE TABLE IF NOT EXISTS reservas(
    id_reserva int AUTO_INCREMENT,
    id_livro INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_reserva),
    FOREIGN KEY (id_livro) REFERENCES livros (id_livro),
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario)
);

