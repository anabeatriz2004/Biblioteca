import com.sun.java.accessibility.util.AWTEventMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Biblioteca {
    Connection conexao = Database.getConexao();

    Livro livro = new Livro();

    JFrame frame;
    JList<Livro> lista = new JList<>();
    DefaultListModel<Livro> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painelInicio = new JPanel(new BorderLayout());
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    // componentes
    JLabel nome = new JLabel("Bem-vindo há biblioteca! :)");
    JButton loginButton = new JButton("Iniciar Sessão");

    public void exibirFrame() {
        frame = new JFrame("Biblioteca");
        // Adiciona a JLabel nome à esquerda no topo
        painelInicio.add(nome, BorderLayout.WEST);

        // Adiciona o LoginButton à direita no topo
        painelInicio.add(loginButton, BorderLayout.EAST);

        // mostra a página do login
        loginButton.addActionListener(e -> {
            frame.dispose(); // fecha a tela inicial
            Login login = new Login();
            login.exibirFrame(); // exibe a tela de login
        });

        // Adiciona o painelInicio ao início do JFrame
        frame.add(painelInicio, BorderLayout.PAGE_START);

        // Restante do código para adicionar as listas e detalhes
        lista.setModel(listaModelo);

        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();

        for (int i = 0; i < todosOsLivros.size(); i++) {
            Livro livro = todosOsLivros.get(i);
            listaModelo.addElement(livro);
        }

        //lista.setCellRenderer(new Biblioteca.LivroRenderer());

        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            exibirDetalhesLivro(livro);
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(lista), BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(painel);

        // Adiciona um ouvinte de evento para o fechamento da janela
        frame.addWindowListener(fecharPrograma());

        frame.add(splitPane, BorderLayout.CENTER); // Adiciona ao centro para ocupar o restante da página
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
                "\nDisponibilidade: " + livro.isDisponibilidade() +
                "\nDescrição: " + livro.getDescricao());

        // Adiciona o botão "Alterar Dados" ao painel direito
        painel.removeAll();
        painel.setLayout(new BorderLayout());
        painel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        painel.revalidate();
        painel.repaint();
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

    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();
        b.exibirFrame();
    }
}
