import java.sql.*;
import java.util.*;

public class Livro {
    private int id_livro;
    private String ISBN;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPubli;
    private String genero;
    private boolean disponibilidade;

    private final Database conexao = new Database();

    public Livro () {}

    // Construtor
    public Livro(int id_livro, String ISBN, String titulo, String autor, String editora, int anoPubli, String genero, boolean disponibilidade) {
        this.id_livro = id_livro;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPubli = anoPubli;
        this.genero = genero;
        this.disponibilidade = disponibilidade;
    }

    // Métodos getter e setter para cada atributo
    public int getID_livro() {
        return id_livro;
    }

    public void setID_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPubli() {
        return anoPubli;
    }

    public void setAnoPubli(int anoPubli) {
        this.anoPubli = anoPubli;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    // Método toString sobrescrito
    @Override
    public String toString() {
        return "Livro {" +
                "idLivro=" + id_livro +
                ", isbn='" + ISBN + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", anoPubli=" + anoPubli +
                ", genero='" + genero + '\'' +
                ", disponibilidade=" + disponibilidade +
                "}\n";
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
    public ArrayList<Livro>  consultarTodosLivros() {
        ArrayList<Livro> todosOsLivros = new ArrayList<>();

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
                    int idLivro = resultado.getInt("id_livro");
                    String isbn = resultado.getString("ISBN");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String editora = resultado.getString("editora");
                    int anoPubli = resultado.getInt("ano_publi");
                    String genero = resultado.getString("genero");
                    boolean disponibilidade = resultado.getBoolean("disponibilidade");

                    // Faça o que deseja com os dados, por exemplo, imprimir na tela
                    /*System.out.println("ID: " + idLivro + ", ISBN: " + isbn + ", Título: " + titulo +
                            ", Autor: " + autor + ", Editora: " + editora + ", Ano de Publicação: " + anoPubli +
                            ", Gênero: " + genero + ", Disponibilidade: " + disponibilidade);*/

                    // Cria uma instância da classe Livro
                    Livro livro = new Livro(idLivro, isbn, titulo, autor, editora, anoPubli, genero, disponibilidade);

                    // Adiciona o livro à lista
                    todosOsLivros.add(livro);
                }
                return todosOsLivros;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // código para inserir livros na base de dados
    public void inserirLivro(Livro livro) {
        try {
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
