import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utilizador {
    //private final Database conexao = new Database();
    Connection conexao = Database.getConexao();

    Livro livro = new Livro();
    Biblioteca biblio = new Biblioteca();

    JFrame frame;
    JList<Livro> lista = new JList<>();
    DefaultListModel<Livro> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painelInicio = new JPanel(new BorderLayout());
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();
    JLabel nome = new JLabel("Entrou como utilizador!");
    JButton devolverButton = new JButton("Devolução");
    JButton emprestimoButton = new JButton("Empréstimo");
    JButton terminarSessaoButton = new JButton("Terminar Sessão");

    public Utilizador() {}

    /**
     * Método para mostrar a lista que é exibida ao arrancar o programa
     */
    public void exibirFrame() {
        listaModelo.clear();
        if (!(listaModelo.isEmpty())) {refreshLivroBaseDados();}

        frame = new JFrame("Biblioteca");

        // Adiciona a JLabel nome à esquerda no topo
        painelInicio.add(nome, BorderLayout.WEST);

        painelInicio.add(terminarSessaoButton, BorderLayout.EAST);

        // mostra a página do login
        terminarSessaoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Falta acresentar o método para terminar sessão.");
            frame.dispose(); // fecha a tela inicial
            biblio.exibirFrame();
        });

        // Adiciona o painelInicio ao início do JFrame
        frame.add(painelInicio, BorderLayout.PAGE_START);

        // Restante do código para adicionar as listas e detalhes
        lista.setModel(listaModelo);

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();
        for (Livro livro : todosOsLivros) {
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

        frame.addWindowListener(fecharPrograma());
        frame.add(splitPane, BorderLayout.CENTER); // Adiciona ao centro para ocupar o restante da página
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Método para mostrar a mostrar todos os detalhes na lista,
     * pode ser usado por três métodos: ListaGenerica(), ListaUtilizador()
     */
    private void exibirDetalhesLivro(Livro livro) {
        textArea.setText("ISBN: " + livro.getISBN() +
                "\nTitulo: " + livro.getTitulo() +
                "\nAutor: " + livro.getAutor() +
                "\nEditora: " + livro.getEditora() +
                "\nAno de Publicação: " + livro.getAnoPubli() +
                "\nGênero: " + livro.getGenero() +
                "\nDisponibilidade: " + livro.getDisponibilidade() +
                "\nDescrição: " + livro.getDescricao());

        // Adiciona o botão "Alterar Dados" ao painel direito
        painel.removeAll();
        painel.setLayout(new BorderLayout());
        painel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        if (livro.isDisponibilidade()) {
            emprestimoButton.addActionListener(e-> { emprestarLivro (livro.getID_livro());});
        } else {
            devolverButton.addActionListener(e-> { devolverLivro(livro.getID_livro());});
        }
        painel.add(emprestimoButton, BorderLayout.NORTH);
        painel.revalidate();
        painel.repaint();
    }

    public void devolverLivro (int id) {
        JOptionPane.showMessageDialog(frame, "Devolver");
    }

    public void emprestarLivro (int id) {
        JOptionPane.showMessageDialog(frame, "Emprestar");
    }

    public int getIdComponenteSelecionado() {
        // Obtém o índice do livro selecionado na lista
        int indiceSelecionado = lista.getSelectedIndex();

        // Verifica se algum livro está selecionado
        if (indiceSelecionado != -1) {
            // Obtém o livro selecionado
            Livro livroSelecionado = listaModelo.getElementAt(indiceSelecionado);

            // Retorna o ID do livro selecionado
            return livroSelecionado.getID_livro();
        } else {
            // Retorna um valor padrão ou lança uma exceção, dependendo do seu requisito
            return -1;  // Valor padrão indicando nenhum livro selecionado
        }
    }

    public void refreshLivroBaseDados() {
        listaModelo.clear();

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();
        for (Livro livroAtual : todosOsLivros) {
            listaModelo.addElement(livroAtual);
            //id_LivroSelecionado = i + 1;
        }

        lista.setCellRenderer(new Bibliotecario.LivroRenderer());

        try {
            lista.getSelectionModel().addListSelectionListener(e -> {
                Livro livroSelecionado = lista.getSelectedValue();
                exibirDetalhesLivro(livroSelecionado);
            });
        } catch (NullPointerException e) {
            System.out.println("erroooooooooooooooooooooooooouuuuuuuuuuuuuuuuuuu");
            e.fillInStackTrace();
        }
    }

    static class LivroRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Livro livro = (Livro) value;
            setText(livro.getTitulo());
            return this;
        }
    }

    private WindowListener fecharPrograma() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    conexao.close();
                    System.exit(0);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    public static void main(String[] args) {
        Utilizador u = new Utilizador();
        u.exibirFrame();
    }
}
