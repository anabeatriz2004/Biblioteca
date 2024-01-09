import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.ArrayList;

public class AdicionarLivroFormulario {
    Connection conexao = Database.getConexao();
    Bibliotecario b = new Bibliotecario();

    JFrame frame;
    public JButton voltarBotao = new JButton("<-- Voltar");

    public final JLabel tituloInicialLabel = new JLabel("Adicionar Livro");

    public final JLabel isbnLabel = new JLabel("ISBN: ");
    public JTextField isbnTextField = new JTextField();
    public final JLabel isbnErroLabel = new JLabel("ISBN sem dados!");

    public final JLabel tituloLabel = new JLabel("Título: ");
    public JTextField tituloTextField = new JTextField();
    public final JLabel tituloErroLabel = new JLabel("Título sem dados!");

    public final JLabel autorLabel = new JLabel("Autor: ");
    public JTextField autorTextField = new JTextField();
    public final JLabel autorErroLabel = new JLabel("Autor sem dados!");

    public final JLabel editoraLabel = new JLabel("Editora: ");
    public JTextField editoraTextField = new JTextField();
    public final JLabel editoraErroLabel = new JLabel();

    public final JLabel anoPubliLabel = new JLabel("Ano de Publicação: ");
    public JTextField anoPubliTextField = new JTextField();
    public final JLabel anoPubliErroLabel = new JLabel("Ano de Publicação sem dados!");

    public final JLabel generoLabel = new JLabel("Gênero: ");
    public JTextField generoTextField = new JTextField();
    public final JLabel generoErroLabel = new JLabel("Gênero sem dados!");

    public final JLabel disponibilidadeLabel = new JLabel("Disponibilidade: ");
    public JTextField disponibilidadeTextField = new JTextField();
    public final JLabel disponibilidadeErroLabel = new JLabel("Disponibilidade sem dados!");

    public final JLabel descricaoLabel = new JLabel("Descricao: ");
    public JTextArea descricaoTextArea = new JTextArea();
    public final JLabel descricaoErroLabel = new JLabel("Descricao sem dados!");

    public JButton adicionarLivroBotao = new JButton("Adicionar Livro");

    AdicionarLivroFormulario() {}

    public void exibirFrame() {
        frame = new JFrame("Formulário - Adicionar Livro");
        frame.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getColorModel();

        voltarBotao.setFont(new Font("Arial", Font.PLAIN, 12));
        voltarBotao.setSize(110, 30);
        voltarBotao.setLocation(0, 0);
        voltarBotao.setForeground(Color.WHITE);
        voltarBotao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        voltarBotao.setBackground(new Color(30, 30, 30));
        voltarBotao.addActionListener(voltarAtras());
        frame.add(voltarBotao);

        tituloInicialLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        tituloInicialLabel.setSize(200, 100);
        tituloInicialLabel.setLocation(650, 50);
        tituloInicialLabel.setForeground(Color.WHITE);
        frame.add(tituloInicialLabel);

        isbnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnLabel.setSize(200, 20);
        isbnLabel.setLocation(100, 150);
        isbnLabel.setForeground(Color.WHITE);
        frame.add(isbnLabel);

        isbnTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnTextField.setSize(1100, 20);
        isbnTextField.setLocation(200, 150);
        frame.add(isbnTextField);

        isbnErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnErroLabel.setSize(200, 20);
        isbnErroLabel.setLocation(200, 175);
        isbnErroLabel.setForeground(Color.WHITE);
        frame.add(isbnErroLabel);

        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloLabel.setSize(200, 20);
        tituloLabel.setLocation(100, 200);
        tituloLabel.setForeground(Color.WHITE);
        frame.add(tituloLabel);

        tituloErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloErroLabel.setSize(200, 20);
        tituloErroLabel.setLocation(200, 225);
        tituloErroLabel.setForeground(Color.WHITE);
        frame.add(tituloErroLabel);

        tituloTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloTextField.setSize(1100, 20);
        tituloTextField.setLocation(200, 200);
        frame.add(tituloTextField);

        autorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        autorLabel.setSize(200, 20);
        autorLabel.setLocation(100, 250);
        autorLabel.setForeground(Color.WHITE);
        frame.add(autorLabel);

        autorErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        autorErroLabel.setSize(200, 20);
        autorErroLabel.setLocation(200, 275);
        autorErroLabel.setForeground(Color.WHITE);
        frame.add(autorErroLabel);

        autorTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        autorTextField.setSize(1100, 20);
        autorTextField.setLocation(200, 250);
        frame.add(autorTextField);

        editoraLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraLabel.setSize(200, 20);
        editoraLabel.setLocation(100, 300);
        editoraLabel.setForeground(Color.WHITE);
        frame.add(editoraLabel);

        editoraErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraErroLabel.setSize(200, 20);
        editoraErroLabel.setLocation(200, 325);
        editoraErroLabel.setForeground(Color.CYAN);
        frame.add(editoraErroLabel);

        editoraTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraTextField.setSize(1100, 20);
        editoraTextField.setLocation(200, 300);
        frame.add(editoraTextField);

        anoPubliLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliLabel.setSize(200, 20);
        anoPubliLabel.setLocation(100, 350);
        anoPubliLabel.setForeground(Color.WHITE);
        frame.add(anoPubliLabel);

        anoPubliErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliErroLabel.setSize(200, 20);
        anoPubliErroLabel.setLocation(200, 375);
        anoPubliErroLabel.setForeground(Color.WHITE);
        frame.add(anoPubliErroLabel);

        anoPubliTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliTextField.setSize(1100, 20);
        anoPubliTextField.setLocation(200, 350);
        frame.add(anoPubliTextField);

        generoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        generoLabel.setSize(200, 20);
        generoLabel.setLocation(100, 400);
        generoLabel.setForeground(Color.WHITE);
        frame.add(generoLabel);

        generoErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        generoErroLabel.setSize(200, 20);
        generoErroLabel.setLocation(200, 425);
        generoErroLabel.setForeground(Color.WHITE);
        frame.add(generoErroLabel);

        generoTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        generoTextField.setSize(1100, 20);
        generoTextField.setLocation(200, 400);
        frame.add(generoTextField);

        disponibilidadeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeLabel.setSize(200, 20);
        disponibilidadeLabel.setLocation(100, 450);
        disponibilidadeLabel.setForeground(Color.WHITE);
        frame.add(disponibilidadeLabel);

        disponibilidadeErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeErroLabel.setSize(200, 20);
        disponibilidadeErroLabel.setLocation(200, 475);
        disponibilidadeErroLabel.setForeground(Color.white);
        frame.add(disponibilidadeErroLabel);

        // mudar a forma de saber se está disponível ou emprestado
        disponibilidadeTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeTextField.setSize(1100, 20);
        disponibilidadeTextField.setLocation(200, 450);
        frame.add(disponibilidadeTextField);

        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoLabel.setSize(200, 20);
        descricaoLabel.setLocation(100, 500);
        descricaoLabel.setForeground(Color.WHITE);
        frame.add(descricaoLabel);

        descricaoErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoErroLabel.setSize(200, 20);
        descricaoErroLabel.setLocation(200, 600);
        descricaoErroLabel.setForeground(Color.WHITE);
        frame.add(descricaoErroLabel);

        descricaoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoTextArea.setSize(1100, 100);
        descricaoTextArea.setLocation(200, 500);
        frame.add(descricaoTextArea);

        adicionarLivroBotao.setFont(new Font("Arial", Font.PLAIN, 20));
        adicionarLivroBotao.setSize(200, 30);
        adicionarLivroBotao.setLocation(650, 615);
        adicionarLivroBotao.setForeground(Color.WHITE);
        adicionarLivroBotao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        adicionarLivroBotao.setBackground(new Color(30, 30, 30));
        adicionarLivroBotao.addActionListener(e-> { adicionarLivro(); });
        frame.add(adicionarLivroBotao);

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

    public Livro verificarDados() {
        // ArrayList<Livro> livroAAdicionar = new ArrayList<>();
        boolean dadoValido;

        // buscar os dados do formulário
        String isbn = isbnTextField.getText();
        String titulo = tituloTextField.getText();
        String autor = autorTextField.getText();
        String editora = editoraTextField.getText();
        String anoPubliStr = anoPubliTextField.getText();
        String genero = generoTextField.getText();
        String disponibilidadeStr = disponibilidadeTextField.getText();
        String descricao = descricaoTextArea.getText();

        int anoPubli = 0;
        boolean disponibilidade = false;

        // vê se o isbn é posto corretamente
        if (isbn.isEmpty()) {
            isbnErroLabel.setText("Isbn sem dados!");
            isbnErroLabel.setForeground(Color.green);
            dadoValido = true;
        } else {
            isbnErroLabel.setText("Isbn sem dados!");
            isbnErroLabel.setForeground(Color.red);
            dadoValido = false;
        }

        if (titulo.isEmpty()) {
            dadoValido = true;
        }

        if (autor.isEmpty()) {
            dadoValido = true;
        }

        if (editora.isEmpty()) {
            dadoValido = true;
        }

        if (anoPubliStr.isEmpty()) {
            dadoValido = true;
        } else {
            try {
                anoPubli = Integer.parseInt(anoPubliStr);
            } catch (NumberFormatException e) {
                // Tratar erro de conversão, se necessário
                e.printStackTrace();
            }
        }

        if (genero.isEmpty()) {
            dadoValido = true;
        }

        if (disponibilidadeStr.isEmpty()) {
            dadoValido = true;
        } else {
            // Supondo que o campo disponibilidade seja representado por "true" ou "false" na entrada
            disponibilidade = Boolean.parseBoolean(disponibilidadeStr);
        }

        if (descricao.isEmpty()) {
            dadoValido = true;
        }

        Livro livro = new Livro(isbn, titulo, autor, editora, anoPubli, genero, disponibilidade, descricao);
        return livro;
    }


    public void adicionarLivro () {

        Livro livroAdicionado = verificarDados();

        livroAdicionado.inserirLivro(livroAdicionado);

        JOptionPane.showMessageDialog(frame, "Nada");
    }

    private WindowListener fecharPrograma() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Chama o método para desconectar do banco de dados
                //conexao.desconectar();

                // Fecha a aplicação
                System.exit(0);
            }
        };
    }
}
