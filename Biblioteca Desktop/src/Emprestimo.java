import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class Emprestimo {
    private int id_livro;
    private int id_utilizador;
    private int id_bibliotecario;
    private LocalDate dataEmprestimo,dataDevolucao;
    private int atraso;
    private Database conexao = null;

    //private Connection conexao;
    Database db = new Database();

    Emprestimo(int id_livro, int id_utilizador, int id_bibliotecario, Database conexao) {
        this.id_livro = id_livro;
        this.id_utilizador = id_utilizador;
        this.id_bibliotecario = id_bibliotecario;
        this.conexao = conexao; // Recebendo a conexão como parâmetro
    }


     /*  private boolean verificar_emprestimo() throws SQLException {
           boolean emprestado = false;
           LocalDate data_devolucao = null;
           LocalDate data_devolvido = null;
           conexao.conectar(); // Conectando ao banco usando a instância de ConexaoMySQL
           String sql = "SELECT data_devolvido, data_devolucao WHERE livro_id = ? AND utilizador_id = ?";
           PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql);
           preparedStatement.setInt(1, livro.getID());
           preparedStatement.setInt(2, utilizador.getID());
           ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
               // Convertendo as strings de data para objetos LocalDate
               data_devolvido = LocalDate.parse(resultSet.getString("data_devolvido"));
               data_devolucao = LocalDate.parse(resultSet.getString("data_devolucao"));

           }
           preparedStatement.close();
           conexao.desconectar(); // Desconectando após a operação// Verificando se as datas são superiores uma a outra
           if (data_devolucao != null  && data_devolvido == null) {
               emprestado = true;
           }
           return emprestado;
        return false;
       }*/


    public void devolverLivro() throws SQLException {
        if (Devolver_Livro(LocalDate.now()) == false) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Livro devolvido atempadamente!", "Devolução", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Livro devolvido com " + atraso + " dias de atraso!", "Devolução", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Boolean Devolver_Livro(LocalDate dataDevolvido) throws SQLException {
        try {
            conexao.conectar(); // Conectando ao banco usando a instância de ConexaoMySQL
            String sql = "UPDATE emprestimos SET data_devolvido = ? WHERE livro_id = ? AND utilizador_id = ?";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql);
            preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(dataDevolvido.atStartOfDay()));
            preparedStatement.setInt(2, id_livro);
            preparedStatement.setInt(3, id_utilizador);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.desconectar(); // Desconectando após a operação
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Verificar_atraso();
    }

    private Boolean Verificar_atraso() throws SQLException {
        boolean atrasado = false;
        LocalDate data_devolucao = null;
        LocalDate data_devolvido = null;
        conexao.conectar(); // Conectando ao banco usando a instância de ConexaoMySQL
        String sql = "SELECT data_devolvido, data_devolucao WHERE livro_id = ? AND utilizador_id = ?";
        PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql);
        preparedStatement.setInt(1, id_livro);
        preparedStatement.setInt(2, id_utilizador);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Convertendo as strings de data para objetos LocalDate
            data_devolvido = LocalDate.parse(resultSet.getString("data_devolvido"));
            data_devolucao = LocalDate.parse(resultSet.getString("data_devolucao"));

        }
        preparedStatement.close();
        conexao.desconectar(); // Desconectando após a operação// Verificando se as datas são superiores uma a outra
        if (data_devolvido.isAfter(data_devolucao)) {
            Period period = Period.between(data_devolvido, data_devolucao);
            atraso = period.getDays();
            atrasado = true;
        }
        return atrasado;
    }

    public void emprestarLivro() {
        try {
            conexao.conectar(); // Conectando ao banco usando a instância de ConexaoMySQL
            dataEmprestimo = LocalDate.now();
            dataDevolucao = LocalDate.now().plusDays(15);
            String sql = "INSERT INTO emprestimo (id_livro, id_utilizador, id_bibliotecario, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql);
            preparedStatement.setInt(1, id_livro);
            preparedStatement.setInt(2, id_utilizador);
            preparedStatement.setInt(3, id_bibliotecario);
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(dataEmprestimo.atStartOfDay()));
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(dataDevolucao.atStartOfDay()));

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conexao.desconectar(); // Desconectando após a operação
            atualizar_estado_livro(false); //Atualiza a disponibilidade do livro
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar_estado_livro(boolean disponibilidade) {
        try {
            conexao.conectar(); // Conectando ao banco usando a instância de Database

            String sql = "UPDATE livro SET disponibilidade = ? WHERE id_livro = ?";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql);
            preparedStatement.setBoolean(1, disponibilidade);
            preparedStatement.setInt(2, id_livro);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conexao.desconectar(); // Desconectando após a operação
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
