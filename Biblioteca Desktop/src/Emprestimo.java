import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class Emprestimo {

    static Connection conexao = Database.getConexao();

    private int id_emprestimo;
    private int id_livro;
    private int id_utilizador;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public int getId_emprestimo() {
        return id_emprestimo;
    }
    public int getId_livro() {
        return id_livro;
    }
    public int getId_utilizador() {
        return id_utilizador;
    }
    public LocalDate getdataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getdataDevolucao() {
        return dataDevolucao;
    }
    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }
    public void setId_utilizador(int id_utilizador) {
        this.id_utilizador = id_utilizador;
    }
    public void setdataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public void setdataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    Emprestimo() {}

    public Emprestimo(int id_livro, int id_utilizador) {
        this.id_livro = id_livro;
        this.id_utilizador = id_utilizador;
    }

    public Emprestimo(int id_emprestimo, int id_livro, int id_utilizador, LocalDate dataEmprestimo,
                      LocalDate dataDevolucao) {
        this.id_emprestimo = id_emprestimo;
        this.id_livro = id_livro;
        this.id_utilizador = id_utilizador;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int obterIdEmprestimo(int idLivro, int idUtilizador) throws SQLException {
        String sql = "SELECT id_emprestimo FROM emprestimo WHERE id_livro = ? AND id_utilizador = ? AND date_devolvido IS NULL";
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

    public static void atualizarDevolucaoLivro(int idEmprestimo, int idLivro) throws SQLException {
        String updateEmprestimo = "UPDATE emprestimo SET date_devolvido = NOW() WHERE id_emprestimo = ?";
        String updateLivro = "UPDATE livro SET disponibilidade = TRUE WHERE id_livro = ?";

        try (PreparedStatement preparedStatementEmprestimo = conexao.prepareStatement(updateEmprestimo);
             PreparedStatement preparedStatementLivro = conexao.prepareStatement(updateLivro)) {

            preparedStatementEmprestimo.setInt(1, idEmprestimo);
            preparedStatementEmprestimo.executeUpdate();

            preparedStatementLivro.setInt(1, idLivro);
            preparedStatementLivro.executeUpdate();
        }
    }


    public void emprestarLivro(int idUtilizador, int idLivro) {
        try {
            dataEmprestimo = LocalDate.now();
            dataDevolucao = LocalDate.now().plusDays(15);

            // Ajuste na ordem dos placeholders e remoção do id_emprestimo da lista de colunas
            String sql = "INSERT INTO emprestimo (id_livro, id_utilizador, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idLivro);
            pstmt.setInt(2, idUtilizador);
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(dataEmprestimo.atStartOfDay()));
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataDevolucao.atStartOfDay()));

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
                                "Livro emprestado com sucesso!" + "\n Devolver livro até dia: " + dataDevolucao,
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