import javax.swing.*;
import java.awt.*;

public class Bibliotecario extends JFrame {
    private JLabel bibliotecaLabel;
    private JLabel BiblioLabel;
    private JPanel BibliotecarioPanel;

    public Bibliotecario() {
        // vai buscar o painel do bibliotecario
        setContentPane(BibliotecarioPanel);
        // nome da aplicação
        setTitle("Biblioteca - Bibliotecario");
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
