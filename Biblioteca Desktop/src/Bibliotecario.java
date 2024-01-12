import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/** A classe Bibliotecario representa a interface do bibliotecário na aplicação de biblioteca. */
public class Bibliotecario {
    // Conexão à base de dados utilizando a classe Database
    Connection conexao = Database.getConexao();

    // Instância da classe Livro para interação com a base de dados
    Livro livro = new Livro();

    // Instância da classe Biblioteca para navegação entre telas
    Biblioteca biblio = new Biblioteca();

    // Componentes da interface gráfica
    JFrame frame;
    JList<Livro> lista = new JList<>();
    DefaultListModel<Livro> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painelInicio = new JPanel(new BorderLayout());
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    // Botões da interface
    JButton adicionarLivroButton = new JButton("Adicionar Livro");
    JButton alterarLivroButton = new JButton("Alterar Dados do Livro");
    JButton eliminarLivroButton = new JButton("Eliminar Livro");
    JButton terminarSessaoButton = new JButton("Terminar Sessão");

    /** Construtor padrão da classe Bibliotecario. */
    Bibliotecario() {}

    /** Método para exibir a frame do bibliotecário, se efetuar login como bibliotecário */
    public void exibirFrame() {
        listaModelo.clear();
        if (!(listaModelo.isEmpty())) {refreshLivroBaseDados();}

        frame = new JFrame("Bibliotecario");

        // Adiciona o LoginButton à direita no topo
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

        // Adiciona um ouvinte de ação para o botão "Adicionar Livro"
        adicionarLivroButton.addActionListener(e -> {
            frame.dispose();
            AdicionarLivroFormulario alf = new AdicionarLivroFormulario();
            alf.exibirFrame();
        });

        // Adiciona um ouvinte de ação para o botão "Alterar Dados"
        // NÃO FUNCIONA
        alterarLivroButton.addActionListener(e -> {
            int idLivroSelecionado = getIdComponenteSelecionado();
            EditarLivroFormulario elf = new EditarLivroFormulario();
            elf.exibirFrame(idLivroSelecionado);
        });

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

        frame.addWindowListener(fecharPrograma());
        frame.add(splitPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Método para obter o ID do livro selecionado na lista.
     * @return ID do livro selecionado. */
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

    /** Método para exibir os detalhes do livro na área de texto.
     * @param livro Objeto Livro para exibir detalhes. */
    private void exibirDetalhesLivro(Livro livro) {
        try {
            textArea.setText("ID" + livro.getID_livro() +
                    "\nISBN: " + livro.getISBN() +
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
            painel.add(alterarLivroButton, BorderLayout.NORTH);
            painel.add(eliminarLivroButton, BorderLayout.EAST);
            painel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            painel.revalidate();
            painel.repaint();
        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }
    }

    /** Método para eliminar um livro da base de dados.
     * @param id_LivroSelecionado ID do livro a ser eliminado. */
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
            JOptionPane.showMessageDialog(frame, "Livro selecionado não existe. " +
                            "\nPor favor, selecione um livro para eliminar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Método para atualizar a lista de livros na base de dados e recriar a lista de modelo. */
    public void refreshLivroBaseDados() {
        listaModelo.clear();

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();
        for (Livro livroAtual : todosOsLivros) {
            listaModelo.addElement(livroAtual);
            //id_LivroSelecionado = i + 1;
        }

        lista.setCellRenderer(new LivroRenderer());

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

    /** Método que é lê se clicou no botão "fechar", e fecha a conexão com a base de dados. */
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

    /**
     * LivroRenderer é uma classe que atua como um renderizador personalizado para uma JList de objetos Livro.
     * Ele estende DefaultListCellRenderer para personalizar a exibição dos elementos na lista.
     */
    static class LivroRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Livro livro = (Livro) value;
            setText(livro.getID_livro() + ": " +  livro.getTitulo());
            return this;
        }
    }
}

