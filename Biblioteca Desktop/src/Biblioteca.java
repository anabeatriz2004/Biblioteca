import com.sun.java.accessibility.util.AWTEventMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/** A classe Biblioteca representa a interface principal da aplicação de biblioteca. */
public class Biblioteca {
    // Conexão à base de dados utilizando a classe Database
    Connection conexao = Database.getConexao();

    // Instância da classe Livro para interação com a base de dados
    Livro livro = new Livro();

    // Componentes da interface gráfica
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

    /** Método para exibir a frame da página inicial da biblioteca. */
    public void exibirFrame() {
        // limpa a lista
        listaModelo.clear();
        // busca novamente a lista de todos os livros da base de dados
        if (!(listaModelo.isEmpty())) {refreshLivroBaseDados();}

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

        // o array todosOsLivros, guarda todos os dados existentes na base de daos
        ArrayList<Livro> todosOsLivros = livro.consultarTodosLivros();

        // percorre o array todosOsLivros e adicina há lista modelo
        for (Livro livro : todosOsLivros) {
            listaModelo.addElement(livro);
        }

        // chama o método para mostrar os títulos dos livros
        lista.setCellRenderer(new Biblioteca.LivroRenderer());

        // Adiciona o ouvinte de seleção de itens na lista
        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            exibirDetalhesLivro(livro);
        });

        // cria um novo painel, que mostra os títulos dos livros do lado direito
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(lista), BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(painel);

        // Adiciona um ouvinte de evento para o fechamento da janela
        frame.addWindowListener(fecharPrograma());

        frame.add(splitPane, BorderLayout.CENTER); // Adiciona ao centro para ocupar o restante da página
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        System.out.prntln(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Método para exibir os detalhes de um livro na interface gráfica.
     * @param livro Objeto Livro para exibir detalhes. */
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
        painel.revalidate();
        painel.repaint();
    }

    /** Método para atualizar a lista de livros na base de dados e recriar a lista de modelo. */
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
            System.out.println("erro");
            e.fillInStackTrace();
        }
    }

    /** Renderer personalizado para a lista de livros. */
    static class LivroRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Livro livro = (Livro) value;
            setText(livro.getTitulo());
            return this;
        }
    }

    /** Método que lê se clicou no botão "fechar" e fecha a conexão com a base de dados.
     * @return Um ouvinte de evento para o fechamento da janela. */
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

    /** Método principal para iniciar a aplicação.
     * @param args Argumentos de linha de comando (não utilizados neste caso). */
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();
        b.exibirFrame();
    }
}
