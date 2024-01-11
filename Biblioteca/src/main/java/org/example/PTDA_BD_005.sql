DROP DATABASE IF EXISTS PTDA_BD_005;
CREATE DATABASE PTDA_BD_005;
USE PTDA_BD_005;

CREATE TABLE utilizador
	(id_utilizador INT PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(50),
	senha Varchar(25),
    telefone INT,
    data_nascimento DATE,
    morada VARCHAR(50));

CREATE TABLE bibliotecario
	(id_bibliotecario INT PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(50),
	senha Varchar(25),
    telefone INT,
    data_contratacao DATE);

CREATE TABLE livro
	(id_livro INT AUTO_INCREMENT PRIMARY KEY,
     ISBN VARCHAR(255),
     titulo VARCHAR(255),
     autor VARCHAR(255),
     editora VARCHAR(255),
     ano_publi INT,
     genero VARCHAR(255),
     disponibilidade BOOLEAN,
     descricao TEXT);

CREATE TABLE emprestimo (
    id_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT,
    id_utilizador INT,
    data_emprestimo DATE,
    data_devolucao DATE,
    FOREIGN KEY (id_livro) REFERENCES livro (id_livro),
    FOREIGN KEY (id_utilizador) REFERENCES utilizador (id_utilizador));


INSERT INTO utilizador
	(id_utilizador, nome, email, senha, telefone, data_nascimento, morada)
    VALUES (1, 'Maria Gonçalves', 'maria_gonc@gmail.com', '123456', 915654783, '2006-1-29', null),
    (2, 'João Ribeiro', 'joao_rib@gmail.com', '123456', 915657438, '2000-1-29', null),
    (3, 'maria', 'maria@gmail.com', '123456', 912345678, '2004-01-01', null);

INSERT INTO bibliotecario
	(id_bibliotecario, nome, email, senha, telefone, data_contratacao)
    VALUES (1, 'Marta Rosado', 'marta_rosado@gmail.com', 'Admin2017', 925484678, '2017-1-29');

INSERT INTO livro (id_livro, ISBN, titulo, autor, editora, ano_publi, genero, disponibilidade, descricao)
VALUES (1, '9780061120084', 'To Kill a Mockingbird', 'Harper Lee', 'Harper Perennial', 1960, 'Fiction', true,
		'A classic novel that explores racial injustice and moral growth in the American South.'),
    (2, '9780142407332', 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 1951, 'Fiction', false,
		'A coming-of-age novel narrated by a teenage boy who has been expelled from prep school.'),
    (3, '9781400032716', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 1925, 'Fiction', true,
		'A story of the American Dream, decadence, and the Roaring Twenties.'),
    (4, '9780062315007', '1984', 'George Orwell', 'Signet Classics', 1949, 'Fiction', true,
		'A dystopian novel that explores the consequences of a totalitarian society.'),
    (5, '9780544003415', 'The Hobbit', 'J.R.R. Tolkien', 'Mariner Books', 1937, 'Fantasy', true,
		'A fantasy novel that follows the journey of Bilbo Baggins as he helps a group of dwarves reclaim their homeland.'),
    (6, '9780062561029', 'To Kill a Kingdom', 'Alexandra Christo', 'Feiwel & Friends', 2018,
		'Young Adult Fantasy', true, 'A dark and twisted reimagining of The Little Mermaid.'),
    (7, '9780735219090', 'Educated', 'Tara Westover', 'Random House', 2018, 'Memoir', true,
		'A memoir about a woman who grows up in a strict and abusive household but eventually escapes to learn about the world.'),
    (8, '9780061124266', 'The Alchemist', 'Paulo Coelho', 'HarperOne', 1988, 'Fiction', true,
		'A philosophical novel that follows the journey of Santiago, a shepherd boy, as he seeks a hidden treasure in Egypt.'),
    (9, '9780062315007', 'The Girl on the Train', 'Paula Hawkins', 'Riverhead Books', 2015, 'Mystery', true,
		'A psychological thriller that explores the lives of three women intertwined by secrets and deception.'),
    (10, '9780062358202', 'Where the Crawdads Sing', 'Delia Owens', 'G.P. Putnam\'s Sons', 2018, 'Mystery', true,
		'A mystery novel that follows the life of Kya Clark, the "Marsh Girl," in the marshes of North Carolina.'),
    (11, '9780141439471', 'Jane Eyre', 'Charlotte Brontë', 'Penguin Classics', 1847, 'Classic', true,
		'A classic novel that tells the story of an orphaned girl, Jane Eyre, and her journey to independence and love.'),
    (12, '9780061125850', 'The Shining', 'Stephen King', 'Anchor Books', 1977, 'Horror', true,
		'A horror novel that follows Jack Torrance and his family as they become the winter caretakers of the haunted Overlook Hotel.'),
    (13, '9780060256654', 'Matilda', 'Roald Dahl', 'Puffin Books', 1988, 'Children\'s Fiction', true,
		'A children\'s novel about a young girl with telekinetic abilities who loves to read and stand up against injustice.'),
    (14, '9780385475723', 'Brave New World', 'Aldous Huxley', 'HarperPerennial', 1932, 'Science Fiction', true,
		'A dystopian novel that explores a futuristic society where advanced technology controls every aspect of life.'),
    (15, '9780061120084', 'The Lord of the Rings', 'J.R.R. Tolkien', 'Mariner Books', 1954, 'Fantasy', true,
		'A fantasy trilogy that follows the quest to destroy the One Ring and defeat the Dark Lord Sauron.');
