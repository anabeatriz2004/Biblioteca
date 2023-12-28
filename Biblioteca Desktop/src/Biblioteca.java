import javax.swing.*;
import java.awt.*;

public class Biblioteca extends JFrame {


    public Biblioteca () {
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
    }

    public static void main(String[] args) {
        new Biblioteca();

    }
}