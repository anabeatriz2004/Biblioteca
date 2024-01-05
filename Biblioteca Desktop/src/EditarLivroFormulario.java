import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditarLivroFormulario {
    Connection conexao = Database.getConexao();

    Bibliotecario b = new Bibliotecario();
    Livro livro = new Livro();

    JFrame frame;
    public JButton voltarBotao = new JButton("<-- Voltar");
    public final JLabel tituloInicialLabel = new JLabel("Editar Livro");
    public final JLabel isbnLabel = new JLabel("ISBN: ");
    public JTextField isbnTextField = new JTextField();
    public final JLabel tituloLabel = new JLabel("Título: ");
    public JTextField tituloTextField = new JTextField();
    public final JLabel autorLabel = new JLabel("Autor: ");
    public JTextField autorTextField = new JTextField();
    public final JLabel editoraLabel = new JLabel("Editora: ");
    public JTextField editoraTextField = new JTextField();
    public final JLabel anoPubliLabel = new JLabel("Ano de Publicação: ");
    public JTextField anoPubliTextField = new JTextField();
    public final JLabel generoLabel = new JLabel("Gênero: ");
    public JTextField generoTextField = new JTextField();
    public final JLabel disponibilidadeLabel = new JLabel("Disponibilidade: ");
    public JTextField disponibilidadeTextField = new JTextField();
    public final JLabel descricaoLabel = new JLabel("Descricao: ");
    public JTextField descricaoTextField = new JTextField();
    public JButton editarlivroButton = new JButton("Editar Livro");

    EditarLivroFormulario() {}

    public void exibirFrame (int idLivroSelecionado) {
        frame = new JFrame("Formulário de Alteração de Dados do Livro");

        ArrayList<Livro> livroArray = new ArrayList<>();

        livroArray = livro.consultarLivro(idLivroSelecionado);

        frame.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getColorModel();

        voltarBotao.setFont(new Font("Arial", Font.PLAIN, 15));
        voltarBotao.setSize(110, 30); // Ajustei a altura
        voltarBotao.setLocation(0, 0); // Ajustei a posição
        voltarBotao.addActionListener(voltarAtras());
        voltarBotao.setForeground(Color.WHITE);
        voltarBotao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        voltarBotao.setBackground(new Color(30, 30, 30));
        frame.add(voltarBotao);

        tituloInicialLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloInicialLabel.setSize(200, 20);
        tituloInicialLabel.setLocation(575, 50);
        tituloInicialLabel.setForeground(Color.WHITE);
        frame.add(tituloInicialLabel);

        isbnLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        isbnLabel.setSize(200, 20);
        isbnLabel.setLocation(575, 150);
        isbnLabel.setForeground(Color.WHITE);
        frame.add(isbnLabel);

        isbnTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        isbnTextField.setSize(200, 20);
        isbnTextField.setLocation(675, 150);
        String isbnLivro = livroArray.get(0).getISBN();
        isbnTextField.setText(isbnLivro);
        frame.add(isbnTextField);

        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloLabel.setSize(200, 20);
        tituloLabel.setLocation(575, 200);
        tituloLabel.setForeground(Color.WHITE);
        frame.add(tituloLabel);

        tituloTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        tituloTextField.setSize(200, 20);
        tituloTextField.setLocation(675, 200);
        String tituloLivro = livroArray.get(0).getTitulo();
        tituloTextField.setText(tituloLivro);
        frame.add(tituloTextField);

        autorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        autorLabel.setSize(200, 20);
        autorLabel.setLocation(575, 250);
        autorLabel.setForeground(Color.WHITE);
        frame.add(autorLabel);

        autorTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        autorTextField.setSize(200, 20);
        autorTextField.setLocation(675, 250);
        String autorLivro = livroArray.get(0).getAutor();
        autorTextField.setText(autorLivro);
        frame.add(autorTextField);

        editoraLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        editoraLabel.setSize(200, 20);
        editoraLabel.setLocation(575, 300);
        editoraLabel.setForeground(Color.WHITE);
        frame.add(editoraLabel);

        editoraTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        editoraTextField.setSize(200, 20);
        editoraTextField.setLocation(675, 300);
        String editoraLivro = livroArray.get(0).getEditora();
        editoraTextField.setText(editoraLivro);
        frame.add(editoraTextField);

        anoPubliLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        anoPubliLabel.setSize(200, 20);
        anoPubliLabel.setLocation(500, 350);
        anoPubliLabel.setForeground(Color.WHITE);
        frame.add(anoPubliLabel);

        anoPubliTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        anoPubliTextField.setSize(200, 20);
        anoPubliTextField.setLocation(675, 350);
        int anoPubliLivro = livroArray.get(0).getAnoPubli();
        anoPubliTextField.setText(String.valueOf(anoPubliLivro));
        frame.add(anoPubliTextField);

        generoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        generoLabel.setSize(200, 20);
        generoLabel.setLocation(575, 400);
        generoLabel.setForeground(Color.WHITE);
        frame.add(generoLabel);

        generoTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        generoTextField.setSize(200, 20);
        generoTextField.setLocation(675, 400);
        String generoLivro = livroArray.get(0).getGenero();
        generoTextField.setText(generoLivro);
        frame.add(generoTextField);

        disponibilidadeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        disponibilidadeLabel.setSize(200, 20);
        disponibilidadeLabel.setLocation(525, 450);
        disponibilidadeLabel.setForeground(Color.WHITE);
        frame.add(disponibilidadeLabel);

        // mudar a forma de saber se está disponível ou emprestado
        disponibilidadeTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        disponibilidadeTextField.setSize(200, 20);
        disponibilidadeTextField.setLocation(675, 450);
        String disponibilidadeLivro = livroArray.get(0).getDisponibilidade();
        disponibilidadeTextField.setText(disponibilidadeLivro);
        frame.add(disponibilidadeTextField);

        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        descricaoLabel.setSize(200, 20);
        descricaoLabel.setLocation(575, 500);
        descricaoLabel.setForeground(Color.WHITE);
        frame.add(descricaoLabel);

        descricaoTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        descricaoTextField.setSize(200, 20);
        descricaoTextField.setLocation(675, 500);
        String descricaoLivro = livroArray.get(0).getDescricao();
        descricaoTextField.setText(descricaoLivro);
        frame.add(descricaoTextField);

        editarlivroButton.setFont(new Font("Arial", Font.PLAIN, 15));
        editarlivroButton.setSize(200, 30);
        editarlivroButton.setLocation(675, 525);
        editarlivroButton.setForeground(Color.WHITE);
        editarlivroButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        editarlivroButton.setBackground(new Color(30, 30, 30));
        editarlivroButton.addActionListener(e-> { editarLivro(); });
        frame.add(editarlivroButton);

        frame.addWindowListener(fecharPrograma());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private ActionListener voltarAtras() {
        return e -> {
            frame.dispose();
            b.exibirFrame();
        };
    }

    private Livro obterDadosLivroEditado() {
        String isbn = isbnTextField.getText();
        String titulo = tituloTextField.getText();
        String autor = autorTextField.getText();
        String editora = editoraTextField.getText();
        String anoPubliStr = anoPubliTextField.getText();
        String genero = generoTextField.getText();
        String disponibilidadeStr = disponibilidadeTextField.getText();
        String descricao = descricaoTextField.getText();

        int anoPubli = 0;
        boolean disponibilidade = true;

        try {
            anoPubli = Integer.parseInt(anoPubliStr);
        } catch (NumberFormatException e) {
            // Tratar erro de conversão, se necessário
            e.printStackTrace();
        }

        // Supondo que o campo disponibilidade seja representado por "true" ou "false" na entrada
        disponibilidade = Boolean.parseBoolean(disponibilidadeStr);

        Livro livroEditado = new Livro(isbn, titulo, autor, editora, anoPubli, genero, disponibilidade, descricao);

        return livroEditado;
    }

    private void editarLivro() {
        Livro livroEditado;
        livroEditado = obterDadosLivroEditado();

        int opcao = JOptionPane.showConfirmDialog(frame,
                "Deseja mesmo alterar o livro com o título: " + livro.getTitulo() + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (opcao == JOptionPane.YES_OPTION) {
            livro.alterarDados(livroEditado);
            frame.dispose();
            b.exibirFrame();
        } else {
            // Se nenhum livro estiver selecionado, exibe uma mensagem de aviso
            JOptionPane.showMessageDialog(frame, "Livro selecionado não existe. " +
                            "\nPor favor, selecione um livro para eliminar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);

        }
    }

    private WindowListener fecharPrograma() {
        // Cria um WindowListener

        // Retorna o WindowListener
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
