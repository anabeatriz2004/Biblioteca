
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Tabela2 extends JFrame {

    Livro l = new Livro();
    private JTable tabela;
    private JPanel panel1;

    Tabela2() {
        // Defina o título
        super("Tabela Demo");

        //
        panel1.setLayout(new FlowLayout());

        // Criando um rótulo (label)
        JLabel rotulo = new JLabel("Texto de Exemplo");

        // Definindo a cor de fundo para preto e a cor de texto para branco
        panel1.setBackground(Color.BLACK);
        rotulo.setForeground(Color.WHITE);

        // Adicionando o rótulo ao panel1
        panel1.add(rotulo);

        // nome das colunas
        String[] colunas = {"id_livro", "ISBN", "titulo", "autor", "editora", "ano de publicação", "gênero", "disponibilidade"};


        // Crie o modelo da tabela
        DefaultTableModel tabelaModelo = (new DefaultTableModel(objetos, colunas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        });

        // identifica o nome das colunas
        tabelaModelo.setColumnIdentifiers(colunas);

        // indica que as colunas estão a 0
        tabelaModelo.setRowCount(0);

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
    private String[][] loadData() {
        try {
            // Armazene os dados em um ArrayList
            ArrayList<Livro> todosOsLivros = l.consultarTodosLivros();

            // Adicione os dados ao modelo da tabela
            for (Livro livro : todosOsLivros) {
                tabelaModelo.addRow(new Object[]{
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

            /*// define o tamanho dos dados de acordo com o tamanho de todos os livros existentes na base de dados
            Object[] dados = new Object[todosOsLivros.size()];

            // Adicione os dados ao modelo da tabela para os novos objetos
            for (int i = 0; i < dados.length; i++) {
                tabelaModelo.addRow(new Object[]{dados[i]});
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // Inicie a aplicação
        new Tabela2();
    }
}
