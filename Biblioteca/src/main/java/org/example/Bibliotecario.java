package org.example;

import java.sql.*;

public class Bibliotecario {
    int id_bibliotecario;
    String nome;
    String email;
    int telefone;
    Date data_contratacao;

    private final Database conexao = new Database(); // Instanciar a classe Database

    // construtor sem dados
    public void Bibliotecario () {}

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
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
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
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
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
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
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

    // método para alterar dados de um livro
    private void alterarLivro(Livro livro) {
        // ID do livro que você deseja alterar
        int idLivroParaAlterar = 1;

        // novos atributos para poder alterar valores
        String novo_ISBN = "";
        String novo_titulo = "";
        String novo_autor = "";
        String novo_editora = "";
        int novo_anoPubli = 0;
        String novo_genero = "";
        boolean novo_disponibilidade = true;


        // Novos dados para o livro
        Livro novoLivro = new Livro();
        novoLivro.setID_livro(idLivroParaAlterar);
        novoLivro.setISBN(novo_ISBN);
        novoLivro.setTitulo(novo_titulo);
        novoLivro.setAutor(novo_autor);
        novoLivro.setEditora(novo_editora);
        novoLivro.setAnoPubli(novo_anoPubli);
        novoLivro.setGenero(novo_genero);
        novoLivro.setDisponibilidade(novo_disponibilidade);

        try {
            // Consulta SQL para atualizar os dados do livro com base no ID
            String sql = "UPDATE livro SET ISBN=?, titulo=?, autor=?, editora=?, anoPubli=?, genero=?, disponibilidade=? WHERE id_livro=?";

            // Cria um PreparedStatement para a consulta SQL
            try (PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql)) {
                // Define os novos dados do livro nos lugares dos pontos de interrogação
                preparedStatement.setString(1, livro.getISBN());
                preparedStatement.setString(2, livro.getTitulo());
                preparedStatement.setString(3, livro.getAutor());
                preparedStatement.setString(4, livro.getEditora());
                preparedStatement.setInt(5, livro.getAnoPubli());
                preparedStatement.setString(6, livro.getGenero());
                preparedStatement.setBoolean(7, livro.isDisponibilidade());
                preparedStatement.setInt(8, livro.getID_livro());

                // Executa a atualização (operação de alteração)
                int linhasAfetadas = preparedStatement.executeUpdate();

                // Verifica se a alteração foi bem-sucedida
                if (linhasAfetadas > 0) {
                    System.out.println("Livro alterado com sucesso!");
                } else {
                    System.out.println("Nenhum livro encontrado com o ID fornecido.");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarLivro(int idLivro) {
        try {
            // Consulta SQL para excluir o livro com base no ID
            String sql = "DELETE FROM livro WHERE id_livro = ?";

            // Cria um PreparedStatement para a consulta SQL
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
                // Define o ID do livro a ser excluído no lugar do ponto de interrogação
                pstmt.setInt(1, idLivro);

                // Executa a atualização (operação de exclusão)
                int linhasAfetadas = pstmt.executeUpdate();

                // Verifica se a exclusão foi bem-sucedida
                if (linhasAfetadas > 0) {
                    System.out.println("Livro excluído com sucesso!");
                } else {
                    System.out.println("Nenhum livro encontrado com o ID fornecido.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}