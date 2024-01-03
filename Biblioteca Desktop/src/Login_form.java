import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login_form {
    JFrame frame = new JFrame("Login");
    public final JLabel biblioLabel = new JLabel("Biblioteca");
    public final JLabel loginLabel = new JLabel("Login");
    public final JLabel emailLabel = new JLabel("Email: ");
    public final JLabel passwordLabel = new JLabel("Password: ");
    public JTextField emailTextField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();
    public JButton logarBotao = new JButton("Logar");

    private final Database conexao = new Database(); // Instanciar a classe Database

    Utilizador u = new Utilizador();
    Bibliotecario b = new Bibliotecario();

    boolean entradaValida = false;

    String senhaIncorreta = "Senha incorreta. Por favor, tente novamente";

    public Login_form() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize);
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);

        biblioLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        biblioLabel.setSize(200, 75);
        biblioLabel.setLocation(580, 175);
        frame.add(biblioLabel);

        loginLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        loginLabel.setSize(50, 25);
        loginLabel.setLocation(100, 500);
        frame.add(loginLabel);

        frame.add(emailLabel);

        frame.add(emailTextField);

        frame.add(passwordLabel);

        frame.add(passwordField);

        logarBotao.setFont(new Font("Arial", Font.PLAIN, 15));
        logarBotao.setSize(100, 20);
        logarBotao.setLocation(270, 450);
        logarBotao.addActionListener(clicarBotaoLogin());
        frame.add(logarBotao);
    }

    private ActionListener clicarBotaoLogin() {
        return e -> {
            String email = emailTextField.getText();
            String senha = String.valueOf(passwordField.getPassword());
            verificarLogin(email, senha);
        };
    }

    public void verificarLogin(String email, String senha) {
        try {
            String utilizadorSql = "SELECT * FROM utilizador WHERE email = ?";
            String bibliotecarioSql = "SELECT * FROM bibliotecario WHERE email = ?";

            try (PreparedStatement pstmtUtilizador = conexao.getConexao().prepareStatement(utilizadorSql);
                 PreparedStatement pstmtBibliotecario = conexao.getConexao().prepareStatement(bibliotecarioSql)) {

                pstmtUtilizador.setString(1, email);
                pstmtBibliotecario.setString(1, email);

                ResultSet resultSetUtilizador = pstmtUtilizador.executeQuery();
                ResultSet resultSetBibliotecario = pstmtBibliotecario.executeQuery();

                if (resultSetUtilizador.next()) {
                    String senhaUtilizador = resultSetUtilizador.getString("senha");
                    if (senhaUtilizador.equals(senha)) {
                        System.out.println("Entrou como utilizador");
                        frame.dispose();
                        u.setVisible(true);
                        entradaValida = true;
                        return;
                    } else {
                        entradaValida = false;
                        System.out.println(senhaIncorreta);
                        JOptionPane.showMessageDialog(frame, senhaIncorreta);
                    }
                }

                if (resultSetBibliotecario.next()) {
                    String senhaBibliotecario = resultSetBibliotecario.getString("senha");
                    if (senhaBibliotecario.equals(senha)) {
                        System.out.println("Entrou como bibliotecario");
                        frame.dispose();
                        b.setVisible(true);
                        entradaValida = true;
                        return;
                    } else {
                        entradaValida = false;
                        System.out.println(senhaIncorreta);
                        JOptionPane.showMessageDialog(frame, senhaIncorreta);
                    }
                }

                entradaValida = false;
                System.out.println("Utilizador não encontrado. Por favor, tente novamente");
                JOptionPane.showMessageDialog(frame, "Utilizador não encontrado. Por favor, tente novamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login_form loginForm = new Login_form();
            loginForm.frame.setVisible(true);
        });
    }
}
