package org.example;

import java.sql.*;

public class Bibliotecario {
    int id_bibliotecario;
    String nome;
    String email;
    int telefone;
    Date data_contratacao;

    private Connection conexao;

    // construtor sem dados
    public void Bibliotecario () {}

    // conecta-se a partir do construtor
    public Bibliotecario(Connection conexao) {
        this.conexao = conexao;
    }

    public int getId_bibliotecario() {
        return id_bibliotecario;
    }

    public void setId_bibliotecario(int id_bibliotecario) {
        this.id_bibliotecario = id_bibliotecario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public Date getData_contratacao() {
        return data_contratacao;
    }

    public void setData_contratacao(Date data_contratacao) {
        this.data_contratacao = data_contratacao;
    }
    
    // Método para consultar um livro pelo ID na base de dados
    public void consultarLivro(int idLivro) {
        try {
            // Define a consulta SQL para selecionar um livro com base no ID
            String sql = "SELECT * FROM livro WHERE id_livro = ?";

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                // Define o parâmetro da consulta com base no ID fornecido
                pstmt.setInt(1, idLivro);

                // Executa a consulta
                ResultSet resultado = pstmt.executeQuery();

                // Processa os resultados
                while (resultado.next()) {
                    // Recupera os dados do livro
                    String isbn = resultado.getString("ISBN");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String editora = resultado.getString("editora");
                    int anoPubli = resultado.getInt("ano_publi");
                    String genero = resultado.getString("genero");
                    boolean disponibilidade = resultado.getBoolean("disponibilidade");

                    // Faça o que deseja com os dados, por exemplo, imprimir na tela
                    System.out.println("ID: " + idLivro + ", ISBN: " + isbn + ", Título: " + titulo +
                            ", Autor: " + autor + ", Editora: " + editora + ", Ano de Publicação: " + anoPubli +
                            ", Gênero: " + genero + ", Disponibilidade: " + disponibilidade);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar todos os livros na base de dados
    public void consultarTodosLivros() {
        try {
            // Define a consulta SQL para selecionar todos os livros
            String sql = "SELECT * FROM livro";

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                // Executa a consulta
                ResultSet resultado = pstmt.executeQuery();

                // Processa os resultados
                while (resultado.next()) {
                    // Recupera os dados do livro
                    long idLivro = resultado.getLong("id_livro");
                    String isbn = resultado.getString("ISBN");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String editora = resultado.getString("editora");
                    int anoPubli = resultado.getInt("ano_publi");
                    String genero = resultado.getString("genero");
                    boolean disponibilidade = resultado.getBoolean("disponibilidade");

                    // Faça o que deseja com os dados, por exemplo, imprimir na tela
                    System.out.println("ID: " + idLivro + ", ISBN: " + isbn + ", Título: " + titulo +
                            ", Autor: " + autor + ", Editora: " + editora + ", Ano de Publicação: " + anoPubli +
                            ", Gênero: " + genero + ", Disponibilidade: " + disponibilidade);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // código para inserir livros na base de dados
    public void inserirLivro(Livro livro) {
        try {
            // Estabelece a conexão com o banco de dados
            //Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            //Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            // Define a consulta SQL para inserir um novo livro
            String sql = "INSERT INTO livro (id_livro, ISBN, titulo, autor, editora, ano_publi, genero, disponibilidade) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Cria o objeto PreparedStatement para evitar SQL Injection
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                // Define os parâmetros da consulta com base no livro fornecido
                pstmt.setLong(1, livro.getID_livro());
                pstmt.setString(2, livro.getISBN());
                pstmt.setString(3, livro.getTitulo());
                pstmt.setString(4, livro.getAutor());
                pstmt.setString(5, livro.getEditora());
                pstmt.setInt(6, livro.getAnoPubli());
                pstmt.setString(7, livro.getGenero());
                pstmt.setBoolean(8, livro.isDisponibilidade());

                // Executa a consulta
                pstmt.executeUpdate();
            }


            System.out.println("Livro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}