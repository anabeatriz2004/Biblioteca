import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Biblioteca extends JFrame {

    private final Database conexao = new Database(); // Instanciar a classe Database

    private JLabel biblioteca;
    private JPanel PaginaInicialPanel;
    private JButton loginBotao;
    private JTable tabela;
    private DefaultTableModel table;

    Login l = new Login();

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

        // Inicializa o modelo da tabela
        tabela = new JTable();
        tabela.addColumn("ID");
        tabela.addColumn("ISBN");
        tabela.addColumn("Título");
        tabela.addColumn("Autor");
        tabela.addColumn("Editora");
        tabela.addColumn("Ano de Publicação");
        tabela.addColumn("Gênero");
        tabela.addColumn("Disponibilidade");

        // Inicializa a tabela com o modelo
        tabela = new JTable(table);

        // Adiciona a tabela a um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(table);
        // Adiciona o JScrollPane ao JFrame
        add(scrollPane);

        // chama o método para vizualizar o tabela com os dados de todos os livros
        consultarTodosLivros();

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

    // Método para consultar todos os livros na base de dados
    public void consultarTodosLivros() {
        try {
            // Define a consulta SQL para selecionar todos os livros
            String sql = "SELECT * FROM livro";

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
                // Executa a consulta
                ResultSet resultado = pstmt.executeQuery();

                // Processa os resultados
                while (resultado.next()) {
                    // Recupera os dados do livro
                    long idLivro = resultado.getLong("id_livro");
                    String isbn = resultado.getString("ISBN");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String editora = resultado.getString("editora");
                    int anoPubli = resultado.getInt("ano_publi");
                    String genero = resultado.getString("genero");
                    boolean disponibilidade = resultado.getBoolean("disponibilidade");

                    // Faça o que deseja com os dados, por exemplo, imprimir na tela
                    System.out.println("ID: " + idLivro + ", ISBN: " + isbn + ", Título: " + titulo +
                            ", Autor: " + autor + ", Editora: " + editora + ", Ano de Publicação: " + anoPubli +
                            ", Gênero: " + genero + ", Disponibilidade: " + disponibilidade);
                }

                while (resultado.next()) {
                    // Adiciona uma nova linha à tabela para cada livro encontrado
                    tabela.addRow(new Object[]{
                            resultado.getLong("id_livro"),
                            resultado.getString("ISBN"),
                            resultado.getString("titulo"),
                            resultado.getString("autor"),
                            resultado.getString("editora"),
                            resultado.getInt("ano_publi"),
                            resultado.getString("genero"),
                            resultado.getBoolean("disponibilidade")
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // inicia a aplicação
        new Biblioteca();
    }
}