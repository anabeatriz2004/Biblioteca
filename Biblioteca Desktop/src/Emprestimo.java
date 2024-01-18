import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/** A classe Emprestimo representa um empréstimo de livro em uma biblioteca.
 * Ela possui métodos para realizar operações relacionadas a empréstimos e interação com a base de dados. */
public class Emprestimo {

    // Conexão estática à base de dados utilizando a classe Database
    static Connection conexao = Database.getConexao();

    // Atributos da classe representando um empréstimo
    private int id_emprestimo;
    private int id_livro;
    private int id_utilizador;
    private int id_bibliotecario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataDevolvido;

    // Métodos getters para os atributos
    public int getId_emprestimo() {
        return id_emprestimo;
    }
    public int getId_livro() {
        return id_livro;
    }
    public int getId_utilizador() {
        return id_utilizador;
    }
    public int getId_bibliotecario() {
        return id_bibliotecario;
    }
    public LocalDate getdataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getdataDevolucao() {
        return dataDevolucao;
    }
    public LocalDate getdataDevolvido() {
        return dataDevolvido;
    }

    // Métodos setters para os atributos
    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }
    public void setId_utilizador(int id_utilizador) {
        this.id_utilizador = id_utilizador;
    }
    public void setId_bibliotecario(int id_bibliotecario) {
        this.id_bibliotecario = id_bibliotecario;
    }
    public void setdataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public void setdataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public void setdataDevolvido(LocalDate dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }

    /** Construtor padrão da classe Emprestimo */
    Emprestimo() {}

    /** Construtor da classe Emprestimo com parâmetros iniciais.
     * @param id_livro ID do livro associado ao empréstimo.
     * @param id_utilizador ID do utilizador associado ao empréstimo. */
    public Emprestimo(int id_livro, int id_utilizador) {
        this.id_livro = id_livro;
        this.id_utilizador = id_utilizador;
    }

    /** Construtor da classe Emprestimo com todos os atributos.
     * @param id_emprestimo ID do empréstimo.
     * @param id_livro ID do livro associado ao empréstimo.
     * @param id_utilizador ID do utilizador associado ao empréstimo.
     * @param dataEmprestimo Data de empréstimo.
     * @param dataDevolucao Data prevista para devolução. */
    public Emprestimo(int id_emprestimo, int id_livro, int id_utilizador, LocalDate dataEmprestimo,
                      LocalDate dataDevolucao) {
        this.id_emprestimo = id_emprestimo;
        this.id_livro = id_livro;
        this.id_utilizador = id_utilizador;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    /** Construtor da classe Emprestimo com todos os atributos.
     * @param id_emprestimo ID do empréstimo.
     * @param id_livro ID do livro associado ao empréstimo.
     * @param id_utilizador ID do utilizador associado ao empréstimo.
     * @param id_bibliotecario ID do bibliotecário responsável pelo empréstimo.
     * @param dataEmprestimo Data de empréstimo.
     * @param dataDevolucao Data prevista para devolução.
     * @param dataDevolvido Data prevista para devolução.*/
    public Emprestimo(int id_emprestimo, int id_livro, int id_utilizador, int id_bibliotecario, LocalDate dataEmprestimo,
                      LocalDate dataDevolucao, LocalDate dataDevolvido) {
        this.id_emprestimo = id_emprestimo;
        this.id_livro = id_livro;
        this.id_utilizador = id_utilizador;
        this.id_bibliotecario = id_bibliotecario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.dataDevolvido = dataDevolvido;
    }

    /**
     * Consulta todos os livros na base de dados.
     * @return ArrayList contendo todos os livros encontrados na base de dados.
     */
    public ArrayList<Emprestimo> consultarTodosEmprestimos() {
        ArrayList<Emprestimo> todosOsEmprestimos = new ArrayList<>();

        try {
            // Define a consulta SQL para selecionar todos os livros
            String sql = "SELECT * FROM emprestimo";

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                // Executa a consulta
                ResultSet resultado = pstmt.executeQuery();

                // Processa os resultados
                while (resultado.next()) {
                    // Recupera os dados do emprestimo
                    int id_emprestimo = resultado.getInt("id_emprestimo");
                    int id_livro = resultado.getInt("id_livro");
                    int id_utilizador = resultado.getInt("id_utilizador");
                    int id_bibliotecario = resultado.getInt("id_bibliotecario");
                    LocalDate data_emprestimo = (resultado.getDate("data_emprestimo") != null) ? resultado.getDate("data_emprestimo").toLocalDate() : null;
                    LocalDate data_devolucao = (resultado.getDate("data_devolucao") != null) ? resultado.getDate("data_devolucao").toLocalDate() : null;
                    LocalDate data_devolvido = (resultado.getDate("data_devolvido") != null) ? resultado.getDate("data_devolvido").toLocalDate() : null;

                    // Cria uma instância da classe Livro
                    Emprestimo livro = new Emprestimo(id_emprestimo, id_livro, id_utilizador, id_bibliotecario, data_emprestimo, data_devolucao, data_devolvido);

                    // Adiciona o livro à lista
                    todosOsEmprestimos.add(livro);
                }
                return todosOsEmprestimos;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Obtém o título do livro associado a um empréstimo.
     * @param id_livro ID do livro.
     * @return Título do livro.
     * @throws SQLException Exceção de SQL. */
    public String obterTituloLivro(int id_livro) throws SQLException {
        String sql = "SELECT titulo FROM livro WHERE livro.id_livro = ?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id_livro);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("titulo");
            } else {
                return "";
            }
        }
    }

    /** Obtém o nome do utilizador associado a um empréstimo.
     * @param id_utilizador ID do utilizador.
     * @return nome do utilizador.
     * @throws SQLException Exceção de SQL.*/
    public String obterNomeUtilizador(int id_utilizador) throws SQLException {
        String sql = "SELECT nome FROM utilizador WHERE id_utilizador = ?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id_utilizador);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("nome");
            } else {
                return "";
            }
        }
    }

    /**Obtém o nome do bibliocário associado a um empréstimo.
     * @param id_bibliotecario ID do bibliotecário.
     * @return nome do bibliocário.
     * @throws SQLException Exceção de SQL.*/
    public String obterNomeBibliotecario(int id_bibliotecario) throws SQLException {
        String sql = "SELECT nome FROM bibliotecario WHERE id_bibliotecario = ?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id_bibliotecario);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("nome");
            } else {
                return "";
            }
        }
    }

    /** Obtém o ID do empréstimo para um livro e utilizador específicos que ainda não foi devolvido.
     * @param idLivro ID do livro.
     * @param idUtilizador ID do utilizador.
     * @return ID do empréstimo ou 0 se não encontrado.
     * @throws SQLException Exceção de SQL. */
    public int obterIdEmprestimo(int idLivro, int idUtilizador) throws SQLException {
        String sql = "SELECT id_emprestimo FROM emprestimo WHERE id_livro = ? AND id_utilizador = ? AND data_devolvido IS NULL";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, idLivro);
        preparedStatement.setInt(2, idUtilizador);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("id_emprestimo");
            } else {
                return 0;
            }
        }
    }

    /** Atualiza a devolução de um livro e marca-o como disponível.
     * @param idEmprestimo ID do empréstimo.
     * @param idLivro ID do livro.
     * @throws SQLException Exceção de SQL. */
    public void atualizarDevolucaoLivro(int idEmprestimo, int idLivro) throws SQLException {
        String updateEmprestimo = "UPDATE emprestimo SET data_devolvido = NOW() WHERE id_emprestimo = ?";
        String updateLivro = "UPDATE livro SET disponibilidade = TRUE WHERE id_livro = ?";

        try (PreparedStatement preparedStatementEmprestimo = conexao.prepareStatement(updateEmprestimo);
             PreparedStatement preparedStatementLivro = conexao.prepareStatement(updateLivro)) {

            preparedStatementEmprestimo.setInt(1, idEmprestimo);
            preparedStatementEmprestimo.executeUpdate();

            preparedStatementLivro.setInt(1, idLivro);
            preparedStatementLivro.executeUpdate();

            JOptionPane.showMessageDialog(new JFrame(),
                    "Livro devolvido com sucesso!" +
                            "\nTem 3 dias para devolver o livro há biblioteca, fisicamente",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /** Realiza o empréstimo de um livro para um utilizador.
     * @param idUtilizador ID do utilizador.
     * @param idLivro ID do livro. */
    public void emprestarLivro(int idUtilizador, int idLivro, int id_bibliotecario) {
        try {
            dataEmprestimo = LocalDate.now();
            dataDevolucao = LocalDate.now().plusDays(15);

            // Ajuste na ordem dos placeholders e remoção do id_emprestimo da lista de colunas
            String sql = "INSERT INTO emprestimo (id_livro, id_utilizador, id_bibliotecario, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idLivro);
            pstmt.setInt(2, idUtilizador);
            pstmt.setInt(3, id_bibliotecario);
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataEmprestimo.atStartOfDay()));
            pstmt.setTimestamp(5, java.sql.Timestamp.valueOf(dataDevolucao.atStartOfDay()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Falha ao inserir empréstimo. Nenhum registro foi afetado.");
                JOptionPane.showMessageDialog(new JFrame(),
                        "Erro ao adicionar dados na tabela emprestimo",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Obtém o ID gerado automaticamente
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idEmprestimo = generatedKeys.getInt(1);
                        System.out.println("Livro emprestado com sucesso!");
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Livro emprestado com sucesso!" +
                                        "\n Devolver livro até dia: " + dataDevolucao +
                                        "\nTem 3 dias para devolver o livro há biblioteca, fisicamente",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Atualiza o estado de disponibilidade de um livro na base de dados.
     * @param idLivro ID do livro.
     * @param disponibilidade Novo estado de disponibilidade (true para disponível, false para indisponível). */
    public void atualizar_estado_livro(int idLivro, boolean disponibilidade) {
        try {
            String sql = "UPDATE livro SET disponibilidade = ? WHERE id_livro = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setBoolean(1, disponibilidade);
            pstmt.setInt(2, idLivro);

            pstmt.executeUpdate();

            //pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
