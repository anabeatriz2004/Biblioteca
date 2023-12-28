import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JPanel LoginPanel;
    private JLabel biblioteca;
    private JLabel LoginLabel;
    private JLabel emailLabel;
    private JLabel PasswordLabel;
    private JTextField EmailTextField;
    private JPasswordField PasswordField;
    private JButton LogarBotao;

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
}
