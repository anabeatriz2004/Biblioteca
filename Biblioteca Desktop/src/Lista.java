import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Lista {

    Livro livro = new Livro();

    int id_LivroSelecionado;

    JFrame frame = new JFrame("Biblioteca");
    JList<Livro> lista = new JList<>();
    DefaultListModel<Livro> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    JButton adicionarLivroButton = new JButton("Adicionar Livro");
    JButton alterarLivroButton = new JButton("Alterar Dados do Livro");
    JButton eliminarLivroButton = new JButton("Eliminar Livro");


    public Lista() {
        ListaBibliotecario();
    }

    /**
     * Método para mostrar a lista que é exibida ao arrancar o programa
     */
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

        JPanel leftPanel = new JPanel(new BorderLayout());
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

    /**
     * Método para mostrar a lista que é exibida, se caso for utilizador
     * pode visualizar todos os livros, pode 'emprestar' e devolver livros
     */
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
        alterarLivroButton.addActionListener(e -> alterarDadosLivro());

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

    /**
     * Método para mostrar a lista que é exibida, se caso for bibliotecario,
     * este pode visualizar todos os livros, tal como o utilizador,
     * pode adicionar livro, alterar livro, e eliminar um livro, também têm acesso há
     * tabela emprestimo, para visualizar os mesmos
     */
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
            exibirDetalhesLivroBibliotecario(livro);
            // refreshLivroBaseDados();
        });

        // Adiciona um ouvinte de ação para o botão "Alterar Dados"
        // NÃO FUNCIONA
        alterarLivroButton.addActionListener(e -> alterarDadosLivro());

        // Adiciona um ouvinte de ação para o botão "Adicionar Livro"
        // NÃO FUNCIONA
        adicionarLivroButton.addActionListener(e -> adicionarLivro());

        // Adiciona um ouvinte de ação para o botão "Eliminar Livro"
        // Obtém o ID do livro selecionado e chama o método eliminarLivro
        eliminarLivroButton.addActionListener(e -> {
            int idSelecionado = getIdComponenteSelecionado();
            eliminarLivro(idSelecionado);
        });

        eliminarLivroButton.setBackground(Color.RED);
        eliminarLivroButton.setForeground(Color.WHITE);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(adicionarLivroButton, BorderLayout.NORTH);

        // Adiciona um ouvinte de mouse para o painel esquerdo
        leftPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtém o ID do componente clicado
                int idSelecionado = getIdComponenteSelecionado();
                System.out.println("ID do componente selecionado: " + idSelecionado);
            }
        });

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

    private int getIdComponenteSelecionado() {
        // Obtém o índice do livro selecionado na lista
        int indiceSelecionado = lista.getSelectedIndex();

        // Verifica se algum livro está selecionado
        if (indiceSelecionado != -1) {
            // Obtém o livro selecionado
            Livro livroSelecionado = listaModelo.getElementAt(indiceSelecionado);

            // Obtém o ID do livro
            int idLivroSelecionado = livroSelecionado.getID_livro();

            // Retorna o ID do livro selecionado
            return idLivroSelecionado;
        } else {
            // Retorna um valor padrão ou lança uma exceção, dependendo do seu requisito
            return -1;  // Valor padrão indicando nenhum livro selecionado
        }
    }

    /**
     * Método para mostrar a mostrar todos os dethalhes na lista,
     * pode ser usado or três mêtodos:
     * ListaGenerica(), ListaUtilizador()
     */
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
        painel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        painel.revalidate();
        painel.repaint();
    }

    /**
     * Método para mostrar a mostrar todos os dethalhes na lista,
     * só pode ser usado pelo mêtodos:
     * ListaBibliotecaria()
     */
    private void exibirDetalhesLivroBibliotecario(Livro livro) {
        textArea.setText("ID" + livro.getID_livro() +
                "\nISBN: " + livro.getISBN() +
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
        painel.add(alterarLivroButton, BorderLayout.NORTH);
        painel.add(eliminarLivroButton, BorderLayout.EAST);
        painel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        painel.revalidate();
        painel.repaint();
    }

    private void adicionarLivro() {
        // Lógica para adicionar um novo livro (pode ser um novo JFrame, JOptionPane, etc.)
        JOptionPane.showMessageDialog(frame, "Implemente a lógica para adicionar um novo livro.");
    }

    private void alterarDadosLivro() {
        // Lógica para alterar os dados do livro (pode ser um novo JFrame, JOptionPane, etc.)
        JOptionPane.showMessageDialog(frame, "Implemente a lógica para alterar os dados do livro.");
    }

    /**
     * Método que é usado pela ListaBibliotecario(), e vai buscar há classe Livro, o método
     * eliminarLivro(), para assim o eliminar
     */
    private void eliminarLivro(int id_LivroSelecionado) {
        // Obtém o índice do livro selecionado na lista
        int indiceSelecionado = lista.getSelectedIndex();

        // Verifica se algum livro está selecionado
        if (indiceSelecionado != -1) {
            // Obtém o livro selecionado
            Livro livroSelecionado = listaModelo.getElementAt(indiceSelecionado);

            // Pergunta de confirmação
            int opcao = JOptionPane.showConfirmDialog(frame,
                    "Deseja mesmo eliminar o livro com o título: " + livroSelecionado.getTitulo() + "?",
                    "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (opcao == JOptionPane.YES_OPTION) {
                // Se o usuário clicar em "Sim", exibe a mensagem de sucesso
                livroSelecionado.eliminarLivro(id_LivroSelecionado);
                JOptionPane.showMessageDialog(frame, "Livro eliminado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                refreshLivroBaseDados();
            }
        } else {
            // Se nenhum livro estiver selecionado, exibe uma mensagem de aviso
            JOptionPane.showMessageDialog(frame, "Por favor, selecione um livro para eliminar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void refreshLivroBaseDados() {
        // Obtém a lista atual de elementos do DefaultListModel
        ArrayList<Livro> elementosAtuais = new ArrayList<>();

        for (int i = 0; i < listaModelo.getSize(); i++) {
            elementosAtuais.add(listaModelo.getElementAt(i));
        }

        // Limpa o DefaultListModel
        listaModelo.clear();

        // Obtém a lista atual de todos os livros na base de dados
        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();

        // Adiciona à listaModelo apenas os elementos que não foram removidos
        for (Livro livro : todosOsLivros) {
            if (elementosAtuais.contains(livro)) {
                listaModelo.addElement(livro);
            }
        }

        lista.setCellRenderer(new LivroRenderer());

        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            exibirDetalhesLivroBibliotecario(livro);
        });
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
