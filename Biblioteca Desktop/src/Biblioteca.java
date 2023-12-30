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
    private JTable jTable;
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

        /*try {
            // Adiciona a tabela a um JScrollPane para permitir rolagem
            JScrollPane scrollPane = new JScrollPane(tabela);

            // Adiciona o JScrollPane ao JFrame
            add(scrollPane);
        } catch (Exception e) {
            e.getMessage();
        }*/

        ExemploJTable();
    }

    // Método para adicionar uma linha à tabela
    private void adicionarLinha(String nome, int idade, String cidade) {
        Object[] rowData = {nome, idade, cidade};
        tableModel.addRow(rowData);
    }

    public void ExemploJTable() {

        // Criação do modelo da tabela
        tableModel = new DefaultTableModel();

        // Adiciona colunas ao modelo
        tableModel.addColumn("Nome");
        tableModel.addColumn("Idade");
        tableModel.addColumn("Cidade");

        // Criação da tabela com o modelo
        //jTable = new JTable(tableModel);
        jTable = new JTable(tableModel);

        // Adiciona a tabela a um JScrollPane (para permitir rolar a tabela)
        //JScrollPane jScrollPane = new JScrollPane(jTable);
        //getContentPane().add(jScrollPane);

        // Adiciona algumas linhas de exemplo
        adicionarLinha("Alice", 25, "Cidade A");
        adicionarLinha("Bob", 30, "Cidade B");

        // Exibe o JFrame
        setVisible(true);
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