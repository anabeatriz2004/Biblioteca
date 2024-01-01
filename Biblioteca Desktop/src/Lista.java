import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lista {

    // Instância da classe Livro
    Livro l = new Livro();

    // Componentes da interface gráfica
    JFrame frame = new JFrame("Biblioteca");
    JList<Livro> lista = new JList<>();
    DefaultListModel<Livro> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    // Construtor da classe Lista
    public Lista() {
        // Configuração do modelo da lista com os livros existentes
        lista.setModel(listaModelo);

        // Consulta todos os livros e adiciona ao modelo da lista
        ArrayList<Livro> todosOsLivros = l.consultarTodosLivros();
        for (int i = 0; i < todosOsLivros.size(); i++) {
            Livro livro = todosOsLivros.get(i);
            listaModelo.addElement(livro);
        }

        // Configuração do renderizador customizado para a lista
        lista.setCellRenderer(new LivroRenderer());

        // Configuração do ouvinte de seleção da lista
        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            exibirDetalhesLivro(livro);
        });

        // Botão para adicionar um novo livro
        JButton adicionarLivroButton = new JButton("Adicionar Livro");
        adicionarLivroButton.addActionListener(e -> adicionarLivro());

        // Configuração do painel esquerdo com o botão e a lista
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(adicionarLivroButton, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(lista), BorderLayout.CENTER);

        // Configuração do painel direito com a área de texto para detalhes do livro
        splitPane.setLeftComponent(leftPanel);
        painel.add(textArea);
        splitPane.setRightComponent(painel);

        // Configuração da frame principal
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Método para exibir os detalhes do livro selecionado
    private void exibirDetalhesLivro(Livro livro) {
        textArea.setText("ISBN: " + livro.getISBN() +
                "\nTitulo: " + livro.getTitulo() +
                "\nAutor: " + livro.getAutor() +
                "\nEditora: " + livro.getEditora() +
                "\nAno de Publicação: " + livro.getAnoPubli() +
                "\nGênero: " + livro.getGenero() +
                "\nDisponibilidade: " + livro.isDisponibilidade());
    }

    // Método para adicionar um novo livro (lógica a ser implementada)
    private void adicionarLivro() {
        // Lógica para adicionar um novo livro (pode ser um novo JFrame, JOptionPane, etc.)
        JOptionPane.showMessageDialog(frame, "Implemente a lógica para adicionar um novo livro.");

    }

    // Renderizador customizado para exibir apenas o título na lista
    class LivroRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Livro livro = (Livro) value;
            setText(livro.getTitulo());
            return this;
        }
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lista::new);
    }
}