import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
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
    public final JLabel isbnErroLabel = new JLabel("");

    public final JLabel tituloLabel = new JLabel("Título: ");
    public JTextField tituloTextField = new JTextField();
    public final JLabel tituloErroLabel = new JLabel("");

    public final JLabel autorLabel = new JLabel("Autor: ");
    public JTextField autorTextField = new JTextField();
    public final JLabel autorErroLabel = new JLabel("");

    public final JLabel editoraLabel = new JLabel("Editora: ");
    public JTextField editoraTextField = new JTextField();
    public final JLabel editoraErroLabel = new JLabel("");

    public final JLabel anoPubliLabel = new JLabel("Ano de Publicação: ");
    public JTextField anoPubliTextField = new JTextField();
    public final JLabel anoPubliErroLabel = new JLabel("");

    public final JLabel generoLabel = new JLabel("Gênero: ");
    public JTextField generoTextField = new JTextField();
    public final JLabel generoErroLabel = new JLabel("");

    public final JLabel disponibilidadeLabel = new JLabel("Disponibilidade: ");
    //public JTextField disponibilidadeTextField = new JTextField();
    public JComboBox<String> disponibilidadeComboBox = new JComboBox<>();
    public final JLabel disponibilidadeErroLabel = new JLabel("");

    public final JLabel descricaoLabel = new JLabel("Descricao: ");
    public JTextArea descricaoTextArea = new JTextArea();
    public final JLabel descricaoErroLabel = new JLabel("");

    public JButton editarLivroBotao = new JButton("Editar Livro");

    EditarLivroFormulario() {}

    public void exibirFrame(int idLivroSelecionado) {
        frame = new JFrame("Formulário de Alteração de Dados do Livro");

        ArrayList<Livro> livroArray;
        livroArray = livro.consultarLivro(idLivroSelecionado);

        frame.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getColorModel();

        voltarBotao.setFont(new Font("Arial", Font.PLAIN, 12));
        voltarBotao.setSize(110, 30);
        voltarBotao.setLocation(0, 0);
        voltarBotao.setForeground(Color.white);
        voltarBotao.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        voltarBotao.setBackground(new Color(30, 30, 30));
        voltarBotao.addActionListener(voltarAtras());
        frame.add(voltarBotao);

        tituloInicialLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        tituloInicialLabel.setSize(200, 100);
        tituloInicialLabel.setLocation(650, 50);
        tituloInicialLabel.setForeground(Color.white);
        frame.add(tituloInicialLabel);

        isbnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnLabel.setSize(200, 20);
        isbnLabel.setLocation(100, 150);
        isbnLabel.setForeground(Color.white);
        frame.add(isbnLabel);

        isbnTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnTextField.setSize(1100, 20);
        isbnTextField.setLocation(200, 150);
        String isbnLivro = livroArray.get(0).getISBN();
        isbnTextField.setText(isbnLivro);
        frame.add(isbnTextField);

        isbnErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnErroLabel.setSize(1100, 20);
        isbnErroLabel.setLocation(200, 175);
        isbnErroLabel.setForeground(Color.white);
        frame.add(isbnErroLabel);

        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloLabel.setSize(200, 20);
        tituloLabel.setLocation(100, 200);
        tituloLabel.setForeground(Color.white);
        frame.add(tituloLabel);

        tituloErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloErroLabel.setSize(1100, 20);
        tituloErroLabel.setLocation(200, 225);
        tituloErroLabel.setForeground(Color.white);
        frame.add(tituloErroLabel);

        tituloTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloTextField.setSize(1100, 20);
        tituloTextField.setLocation(200, 200);
        String tituloLivro = livroArray.get(0).getTitulo();
        tituloTextField.setText(tituloLivro);
        frame.add(tituloTextField);

        autorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        autorLabel.setSize(200, 20);
        autorLabel.setLocation(100, 250);
        autorLabel.setForeground(Color.white);
        frame.add(autorLabel);

        autorErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        autorErroLabel.setSize(1100, 20);
        autorErroLabel.setLocation(200, 275);
        autorErroLabel.setForeground(Color.white);
        frame.add(autorErroLabel);

        autorTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        autorTextField.setSize(1100, 20);
        autorTextField.setLocation(200, 250);
        String autorLivro = livroArray.get(0).getAutor();
        autorTextField.setText(autorLivro);
        frame.add(autorTextField);

        editoraLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraLabel.setSize(200, 20);
        editoraLabel.setLocation(100, 300);
        editoraLabel.setForeground(Color.white);
        frame.add(editoraLabel);

        editoraErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraErroLabel.setSize(1100, 20);
        editoraErroLabel.setLocation(200, 325);
        editoraErroLabel.setForeground(Color.white);
        frame.add(editoraErroLabel);

        editoraTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraTextField.setSize(1100, 20);
        editoraTextField.setLocation(200, 300);
        String editoraLivro = livroArray.get(0).getEditora();
        editoraTextField.setText(editoraLivro);
        frame.add(editoraTextField);

        anoPubliLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliLabel.setSize(200, 20);
        anoPubliLabel.setLocation(100, 350);
        anoPubliLabel.setForeground(Color.white);
        frame.add(anoPubliLabel);

        anoPubliErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliErroLabel.setSize(1100, 20);
        anoPubliErroLabel.setLocation(200, 375);
        anoPubliErroLabel.setForeground(Color.white);
        frame.add(anoPubliErroLabel);

        anoPubliTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliTextField.setSize(1100, 20);
        anoPubliTextField.setLocation(200, 350);
        int anoPubliLivro = livroArray.get(0).getAnoPubli();
        anoPubliTextField.setText(String.valueOf(anoPubliLivro));
        frame.add(anoPubliTextField);

        generoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        generoLabel.setSize(200, 20);
        generoLabel.setLocation(100, 400);
        generoLabel.setForeground(Color.white);
        frame.add(generoLabel);

        generoErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        generoErroLabel.setSize(1100, 20);
        generoErroLabel.setLocation(200, 425);
        generoErroLabel.setForeground(Color.white);
        frame.add(generoErroLabel);

        generoTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        generoTextField.setSize(1100, 20);
        generoTextField.setLocation(200, 400);
        String generoLivro = livroArray.get(0).getGenero();
        generoTextField.setText(generoLivro);
        frame.add(generoTextField);

        disponibilidadeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeLabel.setSize(200, 20);
        disponibilidadeLabel.setLocation(100, 450);
        disponibilidadeLabel.setForeground(Color.white);
        frame.add(disponibilidadeLabel);

        disponibilidadeErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeErroLabel.setSize(1100, 20);
        disponibilidadeErroLabel.setLocation(200, 475);
        disponibilidadeErroLabel.setForeground(Color.white);
        frame.add(disponibilidadeErroLabel);

        // mudar a forma de saber se está disponível ou emprestado
        disponibilidadeComboBox.addItem("Disponível");
        disponibilidadeComboBox.addItem("Emprestado");
        disponibilidadeComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeComboBox.setSize(1100, 20);
        disponibilidadeComboBox.setLocation(200, 450);
        //String disponibilidadeLivro = livroArray.get(0).isDisponibilidade();
        //disponibilidadeTextField.setText(disponibilidadeLivro);
        frame.add(disponibilidadeComboBox);

        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoLabel.setSize(200, 20);
        descricaoLabel.setLocation(100, 500);
        descricaoLabel.setForeground(Color.white);
        frame.add(descricaoLabel);

        descricaoErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoErroLabel.setSize(1100, 20);
        descricaoErroLabel.setLocation(200, 600);
        descricaoErroLabel.setForeground(Color.white);
        frame.add(descricaoErroLabel);

        descricaoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoTextArea.setSize(1100, 100);
        descricaoTextArea.setLocation(200, 500);
        String descricaoLivro = livroArray.get(0).getDescricao();
        descricaoTextArea.setText(descricaoLivro);
        frame.add(descricaoTextArea);

        editarLivroBotao.setFont(new Font("Arial", Font.PLAIN, 20));
        editarLivroBotao.setSize(200, 30);
        editarLivroBotao.setLocation(650, 615);
        editarLivroBotao.setForeground(Color.white);
        editarLivroBotao.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        editarLivroBotao.setBackground(new Color(30, 30, 30));
        editarLivroBotao.addActionListener(e-> { editarLivro(idLivroSelecionado); });
        frame.add(editarLivroBotao);

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

    public Livro verificarDados(int id) {
        boolean dadoValido = false;
        boolean dadoValidoIsbn = false;
        boolean dadoValidoTitulo = false;
        boolean dadoValidoAutor = false;
        boolean dadoValidoEditora = false;
        boolean dadoValidoAnoPubli = false;
        boolean dadoValidoGenero = false;
        boolean dadoValidoDisponibilidade = false;
        boolean dadoValidoDescricao = false;

        // buscar os dados do formulário
        String isbnStr = isbnTextField.getText();
        String titulo = tituloTextField.getText();
        String autor = autorTextField.getText();
        String editora = editoraTextField.getText();
        String anoPubliStr = anoPubliTextField.getText();
        String genero = generoTextField.getText();
        String disponibilidadeStr = (String) disponibilidadeComboBox.getSelectedItem();
        String descricao = descricaoTextArea.getText();

        int anoPubli = 0;
        boolean disponibilidade = true;

        if (isbnStr.isEmpty()) {
            isbnErroLabel.setText("Isbn sem dados!");
            isbnErroLabel.setForeground(Color.yellow);
            dadoValidoIsbn = true;
        } else if (isbnStr.matches("\\d{13}")) {
            isbnErroLabel.setText("ISBN inserido corretamente!");
            isbnErroLabel.setForeground(Color.green);
            dadoValidoIsbn = true;
        } else {
            if (isbnStr.matches(".*\\D.*")) {
                isbnErroLabel.setText("O ISBN só pode conter números!");
            } else {
                isbnErroLabel.setText("Confirme se tem 13 números!");
            }
            isbnErroLabel.setForeground(Color.red);
            dadoValidoIsbn = false;
        }

        if (titulo.isEmpty()) {
            tituloErroLabel.setText("Título sem dados!");
            tituloErroLabel.setForeground(Color.yellow);
            dadoValidoTitulo = true;
        } else {
            tituloErroLabel.setText("Título inserido corretamente!");
            tituloErroLabel.setForeground(Color.green);
            dadoValidoTitulo = true;
        }

        if (autor.isEmpty()) {
            autorErroLabel.setText("Autor sem dados!");
            autorErroLabel.setForeground(Color.yellow);
            dadoValidoAutor = true;
        } else {
            autorErroLabel.setText("Autor inserido corretamente!");
            autorErroLabel.setForeground(Color.green);
            dadoValidoAutor = true;
        }

        if (editora.isEmpty()) {
            editoraErroLabel.setText("Editora sem dados!");
            editoraErroLabel.setForeground(Color.yellow);
            dadoValidoEditora = true;
        } else {
            editoraErroLabel.setText("Editora inserido corretamente!");
            editoraErroLabel.setForeground(Color.green);
            dadoValidoEditora = true;
        }

        // Vê se o anoPubli é inserido corretamente
        if (anoPubliStr.isEmpty()) {
            anoPubliErroLabel.setText("Ano de publicação sem dados!");
            anoPubliErroLabel.setForeground(Color.yellow);
            dadoValidoAnoPubli = true;
        } else {
            try {
                // Tenta converter a string anoPubliStr para um número inteiro
                anoPubli = Integer.parseInt(anoPubliStr);

                // Verificar se o anoPubli está dentro de um intervalo específico (por exemplo, 1000 a 3000)
                if (anoPubli >= 0 && anoPubli <= 2024) {
                    anoPubliErroLabel.setText("Ano de publicação inserido corretamente!");
                    anoPubliErroLabel.setForeground(Color.green);
                    dadoValidoAnoPubli = true;
                } else {
                    anoPubliErroLabel.setText("O ano de publicação deve estar entre 0 e 2024!");
                    anoPubliErroLabel.setForeground(Color.red);
                    dadoValidoAnoPubli = false;
                }
            } catch (NumberFormatException e) {
                anoPubliErroLabel.setText("Confirme se inseriu um número válido para o ano de publicação!");
                anoPubliErroLabel.setForeground(Color.red);
                dadoValidoAnoPubli = false;
            }
        }

        if (genero.isEmpty()) {
            generoErroLabel.setText("Gênero sem dados!");
            generoErroLabel.setForeground(Color.yellow);
            dadoValidoGenero = true;
        } else {
            generoErroLabel.setText("Gênero inserido corretamente!");
            generoErroLabel.setForeground(Color.green);
            dadoValidoGenero = true;
        }

        if (disponibilidadeStr.equals("Disponível")) {
            disponibilidadeErroLabel.setText("Declarou que o livro se encontra disponivel.");
            disponibilidadeErroLabel.setForeground(Color.green);
            disponibilidade = true;
            dadoValidoDisponibilidade = true;
        } else if (disponibilidadeStr.equals("Emprestado")) {
            disponibilidadeErroLabel.setText("Declarou que o livro foi emprestado.");
            disponibilidadeErroLabel.setForeground(Color.green);
            disponibilidade = false;
            dadoValidoDisponibilidade = true;
        }

        if (descricao.isEmpty()) {
            descricaoErroLabel.setText("Descrição sem dados!");
            descricaoErroLabel.setForeground(Color.yellow);
            dadoValidoDescricao = true;
        } else {
            descricaoErroLabel.setText("Descricao inserido corretamente!");
            descricaoErroLabel.setForeground(Color.green);
            dadoValidoDescricao = true;
        }

        // Verifica se todos os dados são válidos
        if (dadoValidoIsbn && dadoValidoTitulo && dadoValidoAutor && dadoValidoEditora && dadoValidoAnoPubli && dadoValidoGenero && dadoValidoDisponibilidade && dadoValidoDescricao) {
            Livro livro = new Livro(id, isbnStr, titulo, autor, editora, anoPubli, genero, disponibilidade, descricao);
            dadoValido = true;
            return livro;
        } else {
            // Se algum dado não for válido, não permita a criação do livro e retorne null
            return null;
        }
    }

    public void editarLivro(int id) {
        Livro livroEditado = verificarDados(id);
        livroEditado.alterarDados(livroEditado);
        frame.dispose();
        b.exibirFrame();
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
