import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lista {

    Livro livro = new Livro();

    JFrame frame = new JFrame("Biblioteca");
    JList<Livro> lista = new JList<>();
    DefaultListModel<Livro> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    JButton alterarDadosButton = new JButton("Alterar Dados");
    JButton adicionarLivroButton = new JButton("Adicionar Livro");

    public Lista() {ListaGenerica();}

    /** Método para mostrar a lista que é exibida ao arrancar o programa */
    public void ListaGenerica() {
        lista.setModel(listaModelo);

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();
        for (int i = 0; i < todosOsLivros.size(); i++) {
            Livro livro = todosOsLivros.get(i);
            listaModelo.addElement(livro);
        }

        lista.setCellRenderer(new LivroRenderer());

        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            exibirDetalhesLivro(livro);
        });

        // Adiciona um ouvinte de ação para o botão "Alterar Dados"
        alterarDadosButton.addActionListener(e -> alterarDadosLivro());

        // Adiciona um ouvinte de ação para o botão "Adicionar Livro"
        adicionarLivroButton.addActionListener(e -> adicionarLivro());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(adicionarLivroButton, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(lista), BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(painel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Método para mostrar a lista que é exibida, se caso for utilizador
     * pode visualizar todos os livros, pode 'emprestar' e devolver livros */
    public void ListaUtilizador() {
        lista.setModel(listaModelo);

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();
        for (int i = 0; i < todosOsLivros.size(); i++) {
            Livro livro = todosOsLivros.get(i);
            listaModelo.addElement(livro);
        }

        lista.setCellRenderer(new LivroRenderer());

        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            exibirDetalhesLivro(livro);
        });

        // Adiciona um ouvinte de ação para o botão "Alterar Dados"
        alterarDadosButton.addActionListener(e -> alterarDadosLivro());

        // Adiciona um ouvinte de ação para o botão "Adicionar Livro"
        adicionarLivroButton.addActionListener(e -> adicionarLivro());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(adicionarLivroButton, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(lista), BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(painel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Método para mostrar a lista que é exibida, se caso for bibliotecario,
     * este pode visualizar todos os livros, tal como o utilizador,
     * pode adicionar livro, alterar livro, e eliminar um livro, também têm acesso há
     * tabela emprestimo, para visualizar os mesmos*/
    public void ListaBibliotecario() {
        lista.setModel(listaModelo);

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();
        for (int i = 0; i < todosOsLivros.size(); i++) {
            Livro livro = todosOsLivros.get(i);
            listaModelo.addElement(livro);
        }

        lista.setCellRenderer(new LivroRenderer());

        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            exibirDetalhesLivro(livro);
        });

        // Adiciona um ouvinte de ação para o botão "Alterar Dados"
        // NÃO FUNCIONA
        alterarDadosButton.addActionListener(e -> alterarDadosLivro());

        // Adiciona um ouvinte de ação para o botão "Adicionar Livro"
        adicionarLivroButton.addActionListener(e -> adicionarLivro());
        // NÃO FUNCIONA

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(adicionarLivroButton, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(lista), BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(painel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Método para mostrar a mostrar todos os dethalhes na lista,
     * pode ser usado or três mêtodos:
     * ListaGenerica(), ListaUtilizador(), ListaBibliotecario() */
    private void exibirDetalhesLivro(Livro livro) {
        textArea.setText("ISBN: " + livro.getISBN() +
                "\nTitulo: " + livro.getTitulo() +
                "\nAutor: " + livro.getAutor() +
                "\nEditora: " + livro.getEditora() +
                "\nAno de Publicação: " + livro.getAnoPubli() +
                "\nGênero: " + livro.getGenero() +
                "\nDisponibilidade: " + livro.isDisponibilidade() +
                "\nDescrição: " + livro.getDescricao());

        // Adiciona o botão "Alterar Dados" ao painel direito
        painel.removeAll();
        painel.setLayout(new BorderLayout());
        painel.add(alterarDadosButton, BorderLayout.NORTH);
        painel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        painel.revalidate();
        painel.repaint();
    }

    private void alterarDadosLivro() {
        // Lógica para alterar os dados do livro (pode ser um novo JFrame, JOptionPane, etc.)
        JOptionPane.showMessageDialog(frame, "Implemente a lógica para alterar os dados do livro.");
    }

    private void adicionarLivro() {
        // Lógica para adicionar um novo livro (pode ser um novo JFrame, JOptionPane, etc.)
        JOptionPane.showMessageDialog(frame, "Implemente a lógica para adicionar um novo livro.");
    }

    class LivroRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Livro livro = (Livro) value;
            setText(livro.getTitulo());
            return this;
        }
    }

    public static void main(String[] args) {
        new Lista();
    }
}
