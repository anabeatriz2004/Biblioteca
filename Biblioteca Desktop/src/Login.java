import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

public class Login extends JFrame {
    private JPanel LoginPanel;
    private JLabel biblioteca;
    private JLabel LoginLabel;
    private JLabel emailLabel;
    private JLabel PasswordLabel;
    private JTextField EmailTextField;
    private JPasswordField PasswordField;
    private JButton LogarBotao;

    private Database conexao = new Database(); // Instanciar a classe Database

    Utilizador u = new Utilizador();
    Bibliotecario b = new Bibliotecario();

    boolean entradaValida = false;

    public Login() {
        // vai buscar o painel do login
        setContentPane(LoginPanel);
        // nome da aplicação
        setTitle("Biblioteca - Login");
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

        // Botão para verificar o login
        LogarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // o email fica com o valor de EmailTextField
                String email = EmailTextField.getText();
                // a senha fica com o valor de PasswordField
                String senha = String.valueOf(PasswordField.getPassword());

                verificarLogin(email, senha);
            }
        });
    }

    public void verificarLogin(String email, String senha) {
        try {
            // Define a consulta SQL para selecionar um utilizador com base no email
            String UtilizadorSql = "SELECT * FROM utilizador WHERE email = ?";
            // Define a consulta SQL para selecionar um bibliotecario com base no email
            String bibliotecarioSql = "SELECT * FROM bibliotecario WHERE email = ?";

            // Cria os objetos PreparedStatement para utilizador e bibliotecario
            try (PreparedStatement pstmtUtilizador = conexao.getConexao().prepareStatement(UtilizadorSql);
                 PreparedStatement pstmtBibliotecario = conexao.getConexao().prepareStatement(bibliotecarioSql)) {

                // Define o parâmetro da consulta com base no email fornecido
                pstmtUtilizador.setString(1, email);
                pstmtBibliotecario.setString(1, email);

                // Executa as consultas
                ResultSet resultSetUtilizador = pstmtUtilizador.executeQuery();
                ResultSet resultSetBibliotecario = pstmtBibliotecario.executeQuery();

                // Verifica se o email foi encontrado na tabela de utilizador
                if (resultSetUtilizador.next()) {
                    // Email encontrado, verifica a senha
                    String senhaUtilizador = resultSetUtilizador.getString("senha");
                    if (senhaUtilizador.equals(senha)) {
                        // Senha correta, login bem-sucedido como utilizador
                        System.out.println("Entrou como utilizador");
                        this.dispose(); // para de se ver a tela de login
                        u.setVisible(true); // ver tela de utilizador
                        entradaValida = true;
                        return;
                    } else {
                        // Senha incorreta para utilizador
                        entradaValida = false;
                        System.out.println("Senha incorreta para utilizador, por favor, tente novamente");
                        return;
                    }
                }

                // Verifica se o email foi encontrado na tabela de bibliotecario
                if (resultSetBibliotecario.next()) {
                    // Email encontrado, verifica a senha
                    String senhaBibliotecario = resultSetBibliotecario.getString("senha");
                    if (senhaBibliotecario.equals(senha)) {
                        // Senha correta, login bem-sucedido como bibliotecario
                        System.out.println("Entrou como bibliotecario");
                        this.dispose(); // para de se ver a tela de login
                        b.setVisible(true); // ver tela de bibliotecario
                        entradaValida = true;
                        return;
                    } else {
                        // Senha incorreta para bibliotecario
                        entradaValida = false;
                        System.out.println("Senha incorreta para bibliotecario, por favor, tente novamente");
                        return;
                    }
                }

                // Email não encontrado em ambas as tabelas
                entradaValida = false;
                System.out.println("Utilizador ou bibliotecario não encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}
