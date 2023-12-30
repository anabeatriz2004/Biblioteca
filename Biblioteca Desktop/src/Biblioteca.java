import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Biblioteca extends JFrame {

    private final Database conexao = new Database(); // Instanciar a classe Database

    private JLabel biblioteca;
    private JPanel PaginaInicialPanel;
    private JButton loginBotao;
    private JPanel TabelaPanel;
    private DefaultTableModel tableModel;

    Login l = new Login();
    private String nome;
    private int idade;
    private String cidade;

    public Biblioteca() {
        // vai buscar o painel Principal
        setContentPane(PaginaInicialPanel);
        // nome da aplicação
        setTitle("Biblioteca");
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

        // Botão Login
        loginBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(Biblioteca.this, "Hello World");
                Biblioteca.this.dispose(); // deixa de ver a tela inicial
                l.setVisible(true); // ver tela login
            }
        });
    }

    public static void main(String[] args) {
        // inicia a aplicação
        SwingUtilities.invokeLater(Biblioteca::new);
    }
}