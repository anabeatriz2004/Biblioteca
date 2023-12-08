import java.sql.*;

public class Bibliotecario {

    int id_bibliotecario;
    String nome;
    String email;
    int telefone;
    Date data_contratacao;

    // Informações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/sua_biblioteca";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA = "sua_senha";

    public void inserirLivro(Livro livro) {
        try {
            // Estabelece a conexão com o banco de dados
            //Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca.sql", "root", null);

            // Define a consulta SQL para inserir um novo livro
            String sql = "INSERT INTO livro (ISBN, titulo, autor, editora, ano_publi, genero, disponibilidade) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Cria o objeto PreparedStatement para evitar SQL Injection
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                // Define os parâmetros da consulta com base no livro fornecido
                pstmt.setString(1, livro.getISBN());
                pstmt.setString(2, livro.getTitulo());
                pstmt.setString(3, livro.getAutor());
                pstmt.setString(4, livro.getEditora());
                pstmt.setInt(5, livro.getAnoPubli());
                pstmt.setString(6, livro.getGenero());
                pstmt.setBoolean(7, livro.isDisponibilidade());

                // Executa a consulta
                pstmt.executeUpdate();
            }

            // Fecha a conexão com o banco de dados
            conexao.close();

            System.out.println("Livro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Outros métodos podem ser adicionados conforme necessário
}

