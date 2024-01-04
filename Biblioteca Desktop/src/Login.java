import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Login {
    JFrame frame = new JFrame("Formulário de Login");
    public final JLabel loginLabel = new JLabel("Login");
    public final JLabel emailLabel = new JLabel("Email: ");
    public final JLabel passwordLabel = new JLabel("Password: ");
    public JTextField emailTextField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();
    public JButton logarBotao = new JButton("Logar");

    //private final Database conexao = new Database(); // Instanciar a classe Database
    Connection conexao = Database.getConexao();

    Utilizador u = new Utilizador();
    Bibliotecario b = new Bibliotecario();

    boolean entradaValida = false;

    String senhaIncorreta = "Senha incorreta. Por favor, tente novamente";

    public Login() {
        frame.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getColorModel();

        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setSize(100, 20);
        loginLabel.setLocation(700, 150);
        loginLabel.setForeground(Color.WHITE);
        frame.add(loginLabel);

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setSize(100, 20);
        emailLabel.setLocation(600, 200);
        emailLabel.setForeground(Color.WHITE);
        frame.add(emailLabel);

        emailTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        emailTextField.setSize(190, 20);
        emailTextField.setLocation(675, 200);
        frame.add(emailTextField);

        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(575, 250);
        passwordLabel.setForeground(Color.WHITE);
        frame.add(passwordLabel);

        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setSize(190, 20);
        passwordField.setLocation(675, 250);
        frame.add(passwordField);

        logarBotao.setFont(new Font("Arial", Font.PLAIN, 15));
        logarBotao.setSize(100, 30); // Ajustei a altura
        logarBotao.setLocation(650, 300); // Ajustei a posição
        logarBotao.addActionListener(clicarBotaoLogin());
        logarBotao.setForeground(Color.WHITE);
        logarBotao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        logarBotao.setBackground(new Color(30, 30, 30));
        frame.add(logarBotao);

        frame.addWindowListener(fecharPrograma());
        frame.setResizable(false);
        frame.setVisible(false);
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

            try (PreparedStatement pstmtUtilizador = conexao.prepareStatement(utilizadorSql);
                 PreparedStatement pstmtBibliotecario = conexao.prepareStatement(bibliotecarioSql)) {

                pstmtUtilizador.setString(1, email);
                pstmtBibliotecario.setString(1, email);

                ResultSet resultSetUtilizador = pstmtUtilizador.executeQuery();
                ResultSet resultSetBibliotecario = pstmtBibliotecario.executeQuery();

                if (resultSetUtilizador.next()) {
                    String senhaUtilizador = resultSetUtilizador.getString("senha");
                    if (senhaUtilizador.equals(senha)) {
                        System.out.println("Entrou como utilizador");
                        frame.dispose();
                        u.exibirFrame();
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
                        b.exibirFrame();
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

    public void exibirFrame() {
        // Define a frame como visível
        frame.setVisible(true);
    }

    private WindowListener fecharPrograma() {
        // Cria um WindowListener
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // Fecha a aplicação
                System.exit(0);
            }
        };

        // Retorna o WindowListener
        return windowListener;
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginForm = new Login();
            loginForm.frame.setVisible(true);
        });
    }*/
}
