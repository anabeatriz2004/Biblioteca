import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Tabela extends JFrame {

    Livro l = new Livro();
    private JTable tabela;
    private DefaultTableModel model;

    Tabela() {
        // Defina o título
        super("Tabela Demo");

        // Crie o modelo da tabela
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"id_livro", "ISBN", "titulo", "autor", "editora", "ano de publicação", "gênero", "disponibilidade"});

        // Crie a tabela com o modelo
        tabela = new JTable(model);

        // Adicione a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicione o JScrollPane à frame
        add(scrollPane);

        // Defina o modo de fechamento
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Defina o tamanho certo da frame
        pack();

        // Para exibir no centro da tela
        setLocationRelativeTo(null);

        // Carregue os dados na tabela
        loadData();

        // Para visualizar
        setVisible(true);
    }

    // Método para carregar todos os métodos na tabela
    private void loadData() {
        try {
            // Armazene os dados em um ArrayList
            ArrayList<Livro> todosOsLivros;

            // Vai buscar os dados de todos os livros
            todosOsLivros = l.consultarTodosLivros();

            // Adicione os dados ao modelo da tabela
            for (Livro livro : todosOsLivros) {
                model.addRow(new Object[]{
                        livro.getID_livro(),
                        livro.getISBN(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.getEditora(),
                        livro.getAnoPubli(),
                        livro.getGenero(),
                        livro.isDisponibilidade()
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // Inicie a aplicação
        new Tabela();
    }
}
