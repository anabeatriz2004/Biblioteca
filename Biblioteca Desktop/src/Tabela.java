
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Tabela extends JFrame {

    Livro l = new Livro();
    private JTable tabela;
    private JPanel panel1;

    Tabela() {
        // vai buscar o painel Principal
        setContentPane(panel1);
        // nome da aplicação
        setTitle("Tabela");
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

        // nome das colunas
        String[] colunas = {"id_livro", "ISBN", "titulo", "autor", "editora", "ano de publicação", "gênero", "disponibilidade"};

        // Carregue os dados na tabela
        String[][] linhas = loadData();

        // Crie o modelo da tabela
        DefaultTableModel tabelaModelo = (new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // diz que a JTable é baseada DefaultTableModel
        tabela.setModel(tabelaModelo);

        // Adicione a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicione o JScrollPane à frame
        add(scrollPane);

        // Para exibir no centro da tela
        setLocationRelativeTo(null);

        // Para visualizar
        setVisible(true);
    }

    // Método para carregar todos os métodos na tabela
    private String[][] loadData() {
        try {
            // Armazene os dados em um ArrayList
            ArrayList<Livro> todosOsLivros = l.consultarTodosLivros();

            // Crie uma matriz para armazenar os dados
            String[][] dados = new String[todosOsLivros.size()][8]; // Assumindo que existem 8 colunas

            // Adicione os dados à matriz
            for (int i = 0; i < todosOsLivros.size(); i++) {
                Livro livro = todosOsLivros.get(i);
                dados[i] = new String[]{
                        String.valueOf(livro.getID_livro()),
                        livro.getISBN(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.getEditora(),
                        String.valueOf(livro.getAnoPubli()),
                        livro.getGenero(),
                        String.valueOf(livro.isDisponibilidade())
                };
            }

            // Retorne a matriz de dados
            return dados;

        } catch (Exception e) {
            // Trate a exceção conforme necessário
            e.printStackTrace();
            return null; // Ou lançar uma exceção, dependendo do seu requisito
        }
    }



    public static void main(String[] args) {
        // Inicie a aplicação
        new Tabela();
    }
}
