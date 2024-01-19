import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/** A classe MostraEmprestimo representa a interface gráfica para visualizar os detalhes dos empréstimos na biblioteca. */
public class MostraEmprestimo{
    // Conexão à base de dados utilizando a classe Database
    Connection conexao = Database.getConexao();

    // Instância da classe emprestimo para interação com a base de dados
    Emprestimo emp = new Emprestimo();
    Bibliotecario b;
    Biblioteca biblio;

    // Componentes da interface gráfica
    JFrame frame;
    JList<Emprestimo> lista = new JList<>();
    DefaultListModel<Emprestimo> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painelInicio = new JPanel(new BorderLayout());
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    // componentes
    JButton voltarButton = new JButton("<-- Voltar");
    JLabel nome = new JLabel("Dados do Empréstimo");
    JButton terminarSessaoButton = new JButton("Terminar Sessão");

    /** Construtor padrão da classe MostraEmprestimo. */
    MostraEmprestimo() {}

    /** Método para exibir a frame da página inicial da biblioteca. */
    public void exibirFrame() {
        // limpa a lista
        listaModelo.clear();
        // busca novamente a lista de todos os emprestimos da base de dados
        if (!(listaModelo.isEmpty())) {refreshEmprestimoBaseDados();}

        frame = new JFrame("Mostrar dados do emprestimo");
        // Adiciona a JLabel nome à esquerda no topo
        painelInicio.add(nome, BorderLayout.WEST);

        // Adiciona o terminarSessaoButton à esquerda no topo
        painelInicio.add(voltarButton, BorderLayout.WEST);

        // exibe a frame da página que mostra os empréstimos
        voltarButton.addActionListener(voltar());

        // Adiciona o LoginButton à direita no topo
        painelInicio.add(terminarSessaoButton, BorderLayout.EAST);

        // mostra a página inicial
        terminarSessaoButton.addActionListener(e -> {
            frame.dispose(); // fecha a tela inicial
            biblio.exibirFrame();
        });

        // Adiciona o painelInicio ao início do JFrame
        frame.add(painelInicio, BorderLayout.PAGE_START);

        // Restante do código para adicionar as listas e detalhes
        lista.setModel(listaModelo);

        // percorre o array todosOsEmprestimos e adicina há lista modelo
        try {
            // o array todosOsEmprestimos, guarda todos os dados existentes na base de daos
            ArrayList<Emprestimo> todosOsEmprestimos = emp.consultarTodosEmprestimos();

            for (Emprestimo emp : todosOsEmprestimos) {
                listaModelo.addElement(emp);
            }
        } catch (NullPointerException e) {
            System.out.println("Não existem dados na tabela emprstimos");
            // e.fillInStackTrace();
        }

        // chama o método para mostrar os ids dos empréstimos
        lista.setCellRenderer(new MostraEmprestimo.EmprestimoRenderer());

        // Adiciona o ouvinte de seleção de itens na lista
        lista.getSelectionModel().addListSelectionListener(e -> {
            Emprestimo emp = lista.getSelectedValue();
            try {
                exibirDetalhesEmprestimo(emp);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // cria um novo painel, que mostra os ids dos emprestimos do lado direito
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

    /**
     * Retorna um ActionListener que fecha a janela atual e exibe a frame anterior.
     * Este ActionListener é utilizado pelo voltarButton.
     * @return Um objeto ActionListener que executa as ações necessárias quando o voltarButton é pressionado.
     */
    private ActionListener voltar() {
        return e -> {
            frame.dispose();
            b.exibirFrame();
        };
    }

    /** Método para exibir os detalhes de um emprestimo na interface gráfica.
     * @param emp Objeto emprestimo para exibir detalhes.
     * @throws SQLException Exceção de SQL. */
    private void exibirDetalhesEmprestimo(Emprestimo emp) throws SQLException {
        textArea.setText("Id do empréstimo: " + emp.getId_emprestimo() +
                "\nLivro: " + emp.getId_livro() + ": " + emp.obterTituloLivro(emp.getId_livro()) +
                "\nUtilizador: " + emp.getId_utilizador() + ": " + emp.obterNomeUtilizador(emp.getId_utilizador()) +
                "\nId do bibliotecario: " + emp.getId_bibliotecario() + ": " + emp.obterNomeBibliotecario(emp.getId_bibliotecario()) +
                "\nData do Empréstimo: " + emp.getdataEmprestimo() +
                "\nData do dia que deverá/deveria devolver: " + emp.getdataDevolucao() +
                "\nData do dia que fora devolvido: " + emp.getdataDevolvido());

        // Adiciona o botão "Alterar Dados" ao painel direito
        painel.removeAll();
        painel.setLayout(new BorderLayout());
        painel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        painel.revalidate();
        painel.repaint();
    }

    /** Método para atualizar a lista de empréstimos na base de dados e recriar a lista de modelo. */
    public void refreshEmprestimoBaseDados() {
        listaModelo.clear();

        ArrayList<Emprestimo> todosOsEmprestimos= emp.consultarTodosEmprestimos();
        for (Emprestimo empAtual : todosOsEmprestimos) {
            listaModelo.addElement(empAtual);
        }

        lista.setCellRenderer(new MostraEmprestimo.EmprestimoRenderer());

        try {
            lista.getSelectionModel().addListSelectionListener(e -> {
                Emprestimo empSelecionado = lista.getSelectedValue();
                try {
                    exibirDetalhesEmprestimo(empSelecionado);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (NullPointerException e) {
            System.out.println("errou");
            e.fillInStackTrace();
        }
    }

    /** Renderer personalizado para a lista de emprestimos */
    static class EmprestimoRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Emprestimo emp = (Emprestimo) value;
            setText(String.valueOf(emp.getId_emprestimo()));
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
        MostraEmprestimo me = new MostraEmprestimo();
        me.exibirFrame();
    }
}
