import java.sql.*;

public class Emprestimo {
    int id_emprestimo;
    int id_livro;
    int id_utilizador;
    int id_bibliotecario;
    Date data_emprestimo;
    Date data_devolucao;

    private Connection conexao;

    // cconstrutor sem dados
    public void Emprestimo () {}

    // conecta-se a partir do construtor
    public Emprestimo(Connection conexao) {
        this.conexao = conexao;
    }
}
