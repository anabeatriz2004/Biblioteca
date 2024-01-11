import javax.swing.*;
import java.sql.*;
import java.time.*;

public class Emprestimo {

    Connection conexao = Database.getConexao();

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

    public void emprestarLivro(int idUtilizador, int idLivro) {
        try {
            dataEmprestimo = LocalDate.now();
            dataDevolucao = LocalDate.now().plusDays(15);
            String sql = "INSERT INTO emprestimo (id_emprestimo, id_livro, id_utilizador, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, idLivro);
            pstmt.setInt(2, idUtilizador);
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataEmprestimo.atStartOfDay()));
            pstmt.setTimestamp(5, java.sql.Timestamp.valueOf(dataDevolucao.atStartOfDay()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Falha ao inserir emprestimo. Nenhum registro foi afetado.");
                JOptionPane.showMessageDialog(new JFrame(),
                        "Erro ao adicionar dados na tabela emprestimo",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Obtém o ID gerado automaticamente
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idEmprestimo = generatedKeys.getInt(1);
                        System.out.println("Livro inserido com sucesso! ID do Livro: " + idEmprestimo);
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Livro inserido com sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            pstmt.executeUpdate();

            pstmt.close();
            atualizar_estado_livro(false); //Atualiza a disponibilidade do livro
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar_estado_livro(boolean disponibilidade) {
        try {

            String sql = "UPDATE livro SET disponibilidade = ? WHERE id_livro = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setBoolean(1, disponibilidade);
            pstmt.setInt(2, id_livro);

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
