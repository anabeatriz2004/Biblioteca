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
    Emprestimo emp = new Emprestimo();

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

    /** Método para exibir a frame do utilizador se entrar como utilizador */
    public void exibirFrame(int idUtilizador) {
        listaModelo.clear();
        if (!(listaModelo.isEmpty())) {refreshLivroBaseDados(idUtilizador);}

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
            exibirDetalhesLivro(idUtilizador, livro);
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
    private void exibirDetalhesLivro(int idUtilizador, Livro livro) {
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
            adicionarBotaoEmprestimo(idUtilizador, livro);
        } else {
            adicionarBotaoDevolucao(idUtilizador, livro);
        }
        painel.revalidate();
        painel.repaint();
    }

    private void adicionarBotaoEmprestimo(int idUtilizador, Livro livro) {
        JButton emprestimoButton = new JButton("Requesitar");
        emprestimoButton.addActionListener(e -> emprestarLivro(idUtilizador, livro.getID_livro()));
        painel.add(emprestimoButton, BorderLayout.NORTH);
    }

    private void adicionarBotaoDevolucao(int idUtilizador, Livro livro) {
        JButton devolverButton = new JButton("Devolução");
        devolverButton.addActionListener(e -> devolverLivro(idUtilizador, livro.getID_livro()));
        painel.add(devolverButton, BorderLayout.NORTH);
    }

    public void emprestarLivro (int idUtilizador, int idLivro) {
        ArrayList<Livro> livroASerLido;
        livroASerLido = livro.consultarLivro(idLivro);
        String titulo = livroASerLido.get(0).getTitulo();
        boolean disponibilidade = !(livroASerLido.get(0).isDisponibilidade());

        int opcao = JOptionPane.showConfirmDialog(frame,
                "Tem a certeza que quer ler o livro: " + titulo + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (opcao == JOptionPane.YES_OPTION) {
            System.out.println("Livro requisitado " + livroASerLido.get(0).getTitulo() + "!");
            emp.emprestarLivro(idUtilizador, idLivro);
            Utilizador u = new Utilizador();
            emp.atualizar_estado_livro(idLivro, disponibilidade);
            frame.dispose();
            u.exibirFrame(idUtilizador);
        } else {
            JOptionPane.showMessageDialog(frame, "Operação cancelada.",
                    "Cancelado", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void devolverLivro (int idUtilizador, int idLivro) {
        ArrayList<Livro> livroASerDevolvido;
        livroASerDevolvido = livro.consultarLivro(idLivro);
        String titulo = livroASerDevolvido.get(0).getTitulo();
        boolean disponibilidade = !(livroASerDevolvido.get(0).isDisponibilidade());
        int idEmprestimo = 0;
        try {
            idEmprestimo = emp.obterIdEmprestimo(idLivro, idUtilizador);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int opcao = JOptionPane.showConfirmDialog(frame,
                "Tem a certeza que quer devolver o livro: " + titulo + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (opcao == JOptionPane.YES_OPTION) {
            System.out.println("Livro devolvido " + livroASerDevolvido.get(0).getTitulo() + "! +" +
                    "\nTem 3 dias para devolver o livro há biblioteca, fisicamente");
            if (idEmprestimo != 0) {
                // Atualizar a data de devolução e disponibilidade do livro
                try {
                    emp.atualizarDevolucaoLivro(idEmprestimo, idLivro);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Livro devolvido com sucesso!");
            } else {
                System.out.println("Livro não emprestado ou já devolvido.");
            }
            Utilizador u = new Utilizador();
            emp.atualizar_estado_livro(idLivro, disponibilidade);
            frame.dispose();
            u.exibirFrame(idUtilizador);
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor." +
                            "Selecione outro livro para devolver.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void refreshLivroBaseDados(int idUtilizador) {
        listaModelo.clear();

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();
        for (Livro livroAtual : todosOsLivros) {
            listaModelo.addElement(livroAtual);
        }

        lista.setCellRenderer(new Bibliotecario.LivroRenderer());

        try {
            lista.getSelectionModel().addListSelectionListener(e -> {
                Livro livroSelecionado = lista.getSelectedValue();
                exibirDetalhesLivro(idUtilizador, livroSelecionado);
            });
        } catch (NullPointerException e) {
            System.out.println("erro");
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

    /** Método que é lê se clicou no botão "fechar", e fecha a conexão com a base de dados*/
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
}
