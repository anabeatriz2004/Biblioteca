import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class Login extends JFrame {
    private JPanel LoginPanel;
    private JLabel biblioteca;
    private JLabel LoginLabel;
    private JLabel emailLabel;
    private JLabel PasswordLabel;
    private JTextField EmailTextField;
    private JPasswordField PasswordField;
    private JButton LogarBotao;

    // private Database conexao;
    private Database conexao = new Database(); // Instanciar a classe Database

    Menu menu = new Menu();

    boolean entradaValida = false;

    public Login() {
        // nome da aplicação
        setTitle("Biblioteca - Login");
        // quando se feche a aplicção o programa termina
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Obtém a resolução da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //define tamanho da aplicação
        setSize(screenSize);
        // para exibir no centro da tela
        setLocationRelativeTo(null);
        // para vizualizar
        setVisible(true);
    }

    public void verificarLogin(String email, String password) {
        try {
            // Define a consulta SQL para selecionar um utilizador com base no email
            String usuarioSql = "SELECT * FROM utilizador WHERE email = ?";
            // Define a consulta SQL para selecionar um bibliotecario com base no email
            String bibliotecarioSql = "SELECT * FROM bibliotecario WHERE email = ?";

            // Cria os objetos PreparedStatement para utilizador e bibliotecario
            try (PreparedStatement pstmtUsuario = conexao.getConexao().prepareStatement(usuarioSql);
                 PreparedStatement pstmtBibliotecario = conexao.getConexao().prepareStatement(bibliotecarioSql)) {

                // Define o parâmetro da consulta com base no email fornecido
                pstmtUsuario.setString(1, email);
                pstmtBibliotecario.setString(1, email);

                // Executa as consultas
                ResultSet resultSetUsuario = pstmtUsuario.executeQuery();
                ResultSet resultSetBibliotecario = pstmtBibliotecario.executeQuery();

                // Verifica se o email foi encontrado na tabela de utilizador
                if (resultSetUsuario.next()) {
                    // Email encontrado, verifica a senha
                    String senhaUsuario = resultSetUsuario.getString("senha");
                    if (senhaUsuario.equals(password)) {
                        // Senha correta, login bem-sucedido como utilizador
                        System.out.println("Entrou como utilizador");
                        menu.percorrerMenuUtilizador();
                        entradaValida = true;
                        return;
                    } else {
                        // Senha incorreta para utilizador
                        entradaValida = false;
                        System.out.println("Senha incorreta para utilizador, por favor, tente novamente");
                        return;
                    }
                }

                // Verifica se o email foi encontrado na tabela de bibliotecario
                if (resultSetBibliotecario.next()) {
                    // Email encontrado, verifica a senha
                    String senhaBibliotecario = resultSetBibliotecario.getString("senha");
                    if (senhaBibliotecario.equals(password)) {
                        // Senha correta, login bem-sucedido como bibliotecario
                        System.out.println("Entrou como bibliotecario");
                        menu.percorrerMenuBibliotecario();
                        entradaValida = true;
                        return;
                    } else {
                        // Senha incorreta para bibliotecario
                        entradaValida = false;
                        System.out.println("Senha incorreta para bibliotecario, por favor, tente novamente");
                        return;
                    }
                }

                // Email não encontrado em ambas as tabelas
                entradaValida = false;
                System.out.println("Utilizador ou bibliotecario não encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}
