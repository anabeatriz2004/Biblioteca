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
    private final DefaultTableModel table;

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

        // Botão Login
        loginBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(Biblioteca.this, "Hello World");
                Biblioteca.this.dispose(); // deixa de ver a tela inicial
                l.setVisible(true); // ver tela login
            }
        });

        // Inicializa o modelo da tabela
        table = new DefaultTableModel();
        table.addColumn("ID");
        table.addColumn("ISBN");
        table.addColumn("Título");
        table.addColumn("Autor");
        table.addColumn("Editora");
        table.addColumn("Ano de Publicação");
        table.addColumn("Gênero");
        table.addColumn("Disponibilidade");

        // Inicializa a tabela com o modelo
        tabela = new JTable(table);

        // chama o método para vizualizar o tabela com os dados de todos os livros
        // consultarTodosLivros();

        try {
            // Adiciona a tabela a um JScrollPane para permitir rolagem
            JScrollPane scrollPane = new JScrollPane(tabela);

            // Adiciona o JScrollPane ao JFrame
            add(scrollPane);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    // Método para consultar todos os livros na base de dados
    /*public void consultarTodosLivros() {
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
                    // Recupera os dados do livro
                    long idLivro = resultado.getLong("id_livro");
                    String isbn = resultado.getString("ISBN");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String editora = resultado.getString("editora");
                    int anoPubli = resultado.getInt("ano_publi");
                    String genero = resultado.getString("genero");
                    boolean disponibilidade = resultado.getBoolean("disponibilidade");

                    // Adiciona uma nova linha à tabela para cada livro encontrado
                    table.addRow(new Object[]{
                            idLivro,
                            isbn,
                            titulo,
                            autor,
                            editora,
                            anoPubli,
                            genero,
                            disponibilidade
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        // inicia a aplicação
        SwingUtilities.invokeLater(Biblioteca::new);
    }
}