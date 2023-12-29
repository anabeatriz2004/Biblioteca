import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Biblioteca extends JFrame {
    private JLabel biblioteca;
    private JPanel PaginaInicialPanel;
    private JButton loginBotao;

    Login l = new Login();

    public Biblioteca() {
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
        new Biblioteca();
    }
}