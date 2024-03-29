import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * A classe Livro representa um livro em uma biblioteca, incluindo métodos para interação com a base de dados.
 */
public class Livro {
    // Conexão à base de dados utilizando a classe Database
    Connection conexao = Database.getConexao();
    // Atributos da classe representando um livro
    private int id_livro;
    private String ISBN;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPubli;
    private String genero;
    private boolean disponibilidade;
    private String descricao;

    /**
     * Construtor padrão da classe Livro.
     */
    public Livro() {
    }

    /**
     * Construtor da classe Livro com parâmetros iniciais.
     *
     * @param ISBN            Número de identificação do livro.
     * @param titulo          Título do livro.
     * @param autor           Autor do livro.
     * @param editora         Editora do livro.
     * @param anoPubli        Ano de publicação do livro.
     * @param genero          Gênero do livro.
     * @param disponibilidade Disponibilidade do livro (true para disponível, false para emprestado).
     * @param descricao       Descrição do livro.
     */
    public Livro(String ISBN, String titulo, String autor, String editora, int anoPubli, String genero, boolean disponibilidade, String descricao) {
        // Inicializa atributos com os parâmetros fornecidos
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPubli = anoPubli;
        this.genero = genero;
        this.disponibilidade = disponibilidade;
        this.descricao = descricao;
    }

    /**
     * Construtor da classe Livro com todos os atributos.
     *
     * @param id_livro        ID do livro.
     * @param ISBN            Número de identificação do livro.
     * @param titulo          Título do livro.
     * @param autor           Autor do livro.
     * @param editora         Editora do livro.
     * @param anoPubli        Ano de publicação do livro.
     * @param genero          Gênero do livro.
     * @param disponibilidade Disponibilidade do livro (true para disponível, false para emprestado).
     * @param descricao       Descrição do livro.
     */
    public Livro(int id_livro, String ISBN, String titulo, String autor, String editora, int anoPubli, String genero, boolean disponibilidade, String descricao) {
        // Inicializa atributos com os parâmetros fornecidos
        this.id_livro = id_livro;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPubli = anoPubli;
        this.genero = genero;
        this.disponibilidade = disponibilidade;
        this.descricao = descricao;
    }

    // Métodos getters e setters para cada atributo
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

    // método para mostrar os dados da disponibilidade do tipo string
    public String getDisponibilidade() {
        if (disponibilidade) {
            return "Disponível";
        } else {
            return "Emprestado";
        }
    }

    // método para mostrar os dados da disponibilidade do tipo boolean
    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma representação de string do objeto Livro.
     *
     * @return String contendo informações detalhadas do livro.
     */
    @Override
    public String toString() {
        return "Livro: " +
                "ISBN: " + ISBN +
                "\n Título: " + titulo +
                "\n Autor: " + autor +
                "\n Editora: " + editora +
                "\n Ano de Publicação: " + anoPubli +
                "\n Gênero: " + genero +
                "\n Disponibilidade: " + disponibilidade +
                "\n Descrição: " + descricao +
                "\n";
    }

    /**
     * Consulta um livro na base de dados pelo ID.
     *
     * @param idLivro ID do livro a ser consultado.
     * @return ArrayList contendo os livros encontrados com o ID especificado.
     */
    public ArrayList<Livro> consultarLivro(int idLivro) {
        ArrayList<Livro> livroArray = new ArrayList<>();
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
                    String descricao = resultado.getString("descricao");

                    Livro livro = new Livro(idLivro, isbn, titulo, autor, editora, anoPubli, genero, disponibilidade, descricao);

                    // Adiciona o livro à lista
                    livroArray.add(livro);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return livroArray;
    }

    /**
     * Consulta todos os livros na base de dados.
     *
     * @return ArrayList contendo todos os livros encontrados na base de dados.
     */
    public ArrayList<Livro> consultarTodosLivros() {
        ArrayList<Livro> todosOsLivros = new ArrayList<>();

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
                    int idLivro = resultado.getInt("id_livro");
                    String isbn = resultado.getString("ISBN");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String editora = resultado.getString("editora");
                    int anoPubli = resultado.getInt("ano_publi");
                    String genero = resultado.getString("genero");
                    boolean disponibilidade = resultado.getBoolean("disponibilidade");
                    String descricao = resultado.getString("descricao");

                    // Cria uma instância da classe Livro
                    Livro livro = new Livro(idLivro, isbn, titulo, autor, editora, anoPubli, genero, disponibilidade, descricao);

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

    /**
     * Insere um novo livro na base de dados.
     *
     * @param livro Objeto Livro a ser inserido na base de dados.
     */
    public void inserirLivro(Livro livro) {
        try {
            // Define a consulta SQL para inserir um novo livro, incluindo todas as colunas, incluindo id_livro
            String sql = "INSERT INTO livro (id_livro, ISBN, titulo, autor, editora, ano_publi, genero, disponibilidade, descricao) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Cria o objeto PreparedStatement para evitar SQL Injection e obter o ID gerado automaticamente
            try (PreparedStatement pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                // Define os parâmetros da consulta
                pstmt.setString(1, livro.getISBN());
                pstmt.setString(2, livro.getTitulo());
                pstmt.setString(3, livro.getAutor());
                pstmt.setString(4, livro.getEditora());
                pstmt.setInt(5, livro.getAnoPubli());
                pstmt.setString(6, livro.getGenero());
                pstmt.setBoolean(7, livro.isDisponibilidade());
                pstmt.setString(8, livro.getDescricao());

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows == 0) {
                    System.out.println("Falha ao inserir o livro. Nenhum registro foi afetado.");
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Erro ao inserir livro na base de dados",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Obtém o ID gerado automaticamente
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int idLivro = generatedKeys.getInt(1);
                            System.out.println("Livro inserido com sucesso! ID do Livro: " + idLivro);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Livro inserido com sucesso!",
                                    "Sucesso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir o livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Altera os dados de um livro na base de dados.
     *
     * @param livro Objeto Livro contendo os novos dados.
     */
    public void alterarDados(Livro livro) {
        try {
            // Query SQL para atualizar os dados do livro
            String sql = "UPDATE livro SET ISBN=?, titulo=?, autor=?, editora=?, ano_publi=?, genero=?, disponibilidade=?, descricao=? WHERE id_livro=?";

            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                // Define os parâmetros na query
                pstmt.setString(1, livro.getISBN());
                pstmt.setString(2, livro.getTitulo());
                pstmt.setString(3, livro.getAutor());
                pstmt.setString(4, livro.getEditora());
                pstmt.setInt(5, livro.getAnoPubli());
                pstmt.setString(6, livro.getGenero());
                pstmt.setBoolean(7, livro.isDisponibilidade());
                pstmt.setString(8, livro.getDescricao());
                pstmt.setInt(9, livro.getID_livro());
                System.out.println(pstmt);

                // Executa a atualização
                pstmt.executeUpdate();
                // conexao.commit(); // Confirmar as alterações

                JOptionPane.showMessageDialog(new JFrame(), "Livro alterado\\editado com sucesso.",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            //conexao.rollback(); // Em caso de erro, fazer o rollback
            e.printStackTrace();
            System.err.println("Erro ao alterar dados do livro: " + e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Erro ao alterar o livro",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Exclui um livro da base de dados pelo ID.
     *
     * @param idLivro ID do livro a ser excluído.
     */
    public void eliminarLivro(int idLivro) {
        try {
            // Consulta SQL para excluir o livro com base no ID
            String sql = "DELETE FROM livro WHERE id_livro = ?";

            // Cria um PreparedStatement para a consulta SQL
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                // Define o ID do livro a ser excluído no lugar do ponto de interrogação
                pstmt.setInt(1, idLivro);

                // Executa a atualização (operação de exclusão)
                int linhasAfetadas = pstmt.executeUpdate();

                // Verifica se a exclusão foi bem-sucedida
                if (linhasAfetadas > 0) {
                    System.out.println("Livro, com id: " + idLivro + " excluído com sucesso!");
                    //consultarTodosLivros();
                    // JOptionPane.showMessageDialog(frame, "Livro eliminado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("Nenhum livro encontrado com o ID fornecido.");
                    //JOptionPane.showMessageDialog(Biblioteca.BibliotecaFrame, "Nenhum livro encontrado com o ID fornecido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
