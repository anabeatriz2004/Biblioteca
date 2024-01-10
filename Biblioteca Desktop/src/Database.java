import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // conexão há base de dados da ua
    /*private static final String url = "jdbc:mysql://estga-dev.ua.pt/PTDA_BD_005";
    private static final String user = "PTDA_005";
    private static final String password = "Zh#pos539";*/

    // Configuração da conexão localhost
    private static final String url = "jdbc:mysql://localhost/PTDA_BD_005";
    private static final String user = "root";
    private static final String password = "RandomGirl2.0";

    // Conexão estática
    private static Connection conexao = null;

    // Bloco estático para inicializar a conexão
    static {
        conectar();
    }

    private Database() {
        // Construtor privado para evitar instanciação externa
    }

    public static Connection getConexao() {
        return conexao;
    }

    private static void conectar() {
        try {
            if (conexao == null || conexao.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão bem-sucedida.");
            }
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

    public static void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão encerrada.");
                JOptionPane.showMessageDialog(new JFrame(), "Conexão encerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
