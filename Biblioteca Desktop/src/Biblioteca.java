import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Biblioteca extends JFrame {
    private JLabel biblioteca;
    private JPanel PaginaInicialPanel;
    private JButton loginBotao;

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
                JOptionPane.showMessageDialog(Biblioteca.this, "Hello World");
            }
        });
    }

    // cria scanner para interagir
    static Scanner scan = new Scanner(System.in);

    private final Database conexao = new Database(); // Instanciar a classe Database

    public static void main(String[] args) {
        new Biblioteca();
        new Login();

        String ub; // variavel que indica quem é
        String email;
        String password;

        VerificarLogin vl = new VerificarLogin();
        Menu menu = new Menu();

        do {
            // saber quem está a utilizar o programa
            System.out.println("\nDiga quem é:");
            System.out.println("Escreva 'u', se for utilizador");
            System.out.println("Escreva 'b', se for bibliotecario");
            System.out.println("Escreva sair, se pretender sair");
            ub = scan.nextLine();

            switch (ub) {
                case "u":
                    System.out.println("Escolheu utilizador");
                    // inserir os dados para logar
                    System.out.println("Insira os dados para efetuar o login");
                    System.out.println("Escreva o seu email:");
                    email = scan.nextLine();
                    System.out.println("Escreva a sua password:");
                    password = scan.nextLine();
                    vl.verificarLoginUtilizador(email, password);
                    break;
                case "b":
                    System.out.println("Escolheu bibliotecario");
                    // inserir os dados para logar
                    System.out.println("Insira os dados para efetuar o login");
                    System.out.println("Escreva o seu email:");
                    email = scan.nextLine();
                    System.out.println("Escreva a sua password:");
                    password = scan.nextLine();
                    vl.verificarLoginBibliotecario(email, password);
                    break;
                case "sair":
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (!ub.equals("sair"));

        // Fechar o scanner
        scan.close();
    }
}