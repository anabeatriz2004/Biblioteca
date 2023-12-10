import java.sql.*;

public class Utilizador {
    int id_utilizador;
    String nome;
    String email;
    int telefone;
    Date data_nascimento;
    String morada;

    private Connection conexao;

    // cconstrutor sem dados
    public void Utilizador () {}

    // conecta-se a partir do construtor
    public Utilizador(Connection conexao) {
        this.conexao = conexao;
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

            // Fecha a conexão com o banco de dados
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
