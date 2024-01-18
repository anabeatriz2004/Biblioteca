import com.sun.java.accessibility.util.AWTEventMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/** A classe Biblioteca representa a interface principal da aplicação de biblioteca. */
public class MostraEmprestimo{
    // Conexão à base de dados utilizando a classe Database
    Connection conexao = Database.getConexao();

    // Instância da classe emprestimo para interação com a base de dados
    Emprestimo emp = new Emprestimo();
    Biblioteca biblio = new Biblioteca();

    // Componentes da interface gráfica
    JFrame frame;
    JList<Emprestimo> lista = new JList<>();
    DefaultListModel<Emprestimo> listaModelo = new DefaultListModel<>();
    JTextArea textArea = new JTextArea();
    JPanel painelInicio = new JPanel(new BorderLayout());
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    // componentes
    JLabel nome = new JLabel("Dados do Empréstimo");
    JButton terminarSessaoButton = new JButton("Terminar Sessão");

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

        // Adiciona o LoginButton à direita no topo
        painelInicio.add(terminarSessaoButton, BorderLayout.EAST);

        // mostra a página inicial
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

        // o array todosOsEmprestimos, guarda todos os dados existentes na base de daos
        ArrayList<Emprestimo> todosOsEmprestimos = emp.consultarTodosEmprestimos();

        // percorre o array todosOsEmprestimos e adicina há lista modelo
        try {
            for (Emprestimo emp : todosOsEmprestimos) {
                listaModelo.addElement(emp);
            }
        } catch (NullPointerException e) {
            // chama o método para mostrar os ids dos empréstimos
            lista.setCellRenderer(new MostraEmprestimo.EmprestimoRendererNulo());
            e.fillInStackTrace();
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

    /** Método para exibir os detalhes de um emprestimo na interface gráfica.
     * @param emp Objeto emprestimo para exibir detalhes. */
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

    /** Método para atualizar a lista de emprestimos na base de dados e recriar a lista de modelo. */
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
    static class EmprestimoRendererNulo extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setText("Não existem dados na base de dados");
            return this;
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
