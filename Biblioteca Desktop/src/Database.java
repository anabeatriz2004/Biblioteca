import javax.swing.*;
import java.sql.*;

public class Database extends JFrame {

    private static final String url = "jdbc:mysql://estga-dev.ua.pt/PTDA_BD_005";
    private static final String user = "PTDA_005";
    private static final String password = "Zh#pos539";

    private Connection conexao = null;

    public Database() {
        conectar();
    }

    public Connection getConexao() {
        return conexao;
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem-sucedida.");
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(),
                    "Erro ao conectar há base de dados",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Encerrando o programa...");
            System.exit(0);
        }
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão encerrada.");
                JOptionPane.showMessageDialog(new JFrame(), "Conexão encerrada");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}