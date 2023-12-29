import javax.swing.*;
import java.awt.*;

public class Utilizador extends JFrame {
    private JLabel BibliotecaLabel;
    private JPanel UtilizadorPanel;
    private JLabel UtilizadorLabel;

    public Utilizador() {
        // vai buscar o painel do utilizador
        setContentPane(UtilizadorPanel);
        // nome da aplicação
        setTitle("Biblioteca - Utilizador");
        // quando se feche a aplicção o programa termina
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Obtém a resolução da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // definir tamanho da aplicação
        setSize(screenSize);
        // exibir no centro da tela
        setLocationRelativeTo(null);
        // visualizar a tela
        setVisible(false);
    }
}
