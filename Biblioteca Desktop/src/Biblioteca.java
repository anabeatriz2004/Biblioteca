import javax.swing.*;

public class Biblioteca extends JFrame {
    public Biblioteca () {
        // nome da aplicação
        setTitle("Biblioteca");
        // quando se feche a aplicção o programa termina
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //define tamanho da aplicação
        setSize(300,200);
        // para exibir no centro da tela
        setLocationRelativeTo(null);
        // para vizualizar
        setVisible(true);
    }
}