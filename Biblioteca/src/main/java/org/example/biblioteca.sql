DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE utilizador
	(id_utilizador INT PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    telefone INT,
    data_nascimento DATE,
    morada VARCHAR(50));

CREATE TABLE bibliotecario
	(id_bibliotecario INT PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    telefone INT,
    data_contratacao DATE);

CREATE TABLE livro
	(id_livro INT PRIMARY KEY,
    ISBN VARCHAR(13),
    titulo VARCHAR(25),
    autor VARCHAR(25),
    editora VARCHAR(25),
    ano_publi INT,
    genero VARCHAR(50),
    disponibilidade BOOLEAN); #true - disponível; false- emprestado

CREATE TABLE emprestimo (
    id_emprestimo INT PRIMARY KEY,
    id_livro INT,
    id_utilizador INT,
    id_bibliotecario INT,
    data_emprestimo DATE,
    data_devolucao DATE,
    FOREIGN KEY (id_livro) REFERENCES livro (id_livro),
    FOREIGN KEY (id_utilizador) REFERENCES utilizador (id_utilizador),
    FOREIGN KEY (id_bibliotecario) REFERENCES bibliotecario (id_bibliotecario));


INSERT INTO utilizador
	(id_utilizador, nome, email, password, telefone, data_nascimento, morada)
    VALUES (1, 'Maria Gonçalves', 'maria_gonc@gmail.com', '123456789', 915654783, '2006-1-29', null),
(2, 'João Ribeiro', 'joao_rib@gmail.com', '123456789', 915657438, '2000-1-29', null);

INSERT INTO bibliotecario
	(id_bibliotecario, nome, email, password, telefone, data_contratacao)
    VALUES (1, 'Marta Rosado', 'marta_rosado@gmail.com', '123456789', 925484678, '2017-1-29');

INSERT INTO livro
	(id_livro, ISBN, titulo, autor, editora, ano_publi, genero, disponibilidade)
    VALUES (1, '9789720049575', 'Os Maias', 'Eça de Queirós', 'Porto Editora', 2017, 'romance', true),
    (2, '9789722100007', 'Uma Aventura na Cidade', 'Ana Maria Magalhães', 'Caminho', 1998, 'infantojuvenil, aventura, mistério', true);

INSERT INTO emprestimo
	(id_emprestimo, id_livro, id_utilizador, id_bibliotecario, data_emprestimo, data_devolucao)
    VALUES (1, 2, 1, 1, '2023-11-15', '2023-11-29');
