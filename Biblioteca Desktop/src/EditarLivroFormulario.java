import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EditarLivroFormulario {
    Bibliotecario b = new Bibliotecario();
    Livro livro = new Livro();

    Livro livroSelecionado = b.alterarDadosLivro();

    JFrame frame = new JFrame("Formulário de Alteração de Dados do Livro");
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
    public JButton editarlivroButton = new JButton("Adicionar Livro");

    EditarLivroFormulario() {
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
        //Livro livroSelecionado = livroSelecionado.consultarLivro(id_LivroSelecionado); /* lógica para obter o livro selecionado */
        //String isbnLivro = livroSelecionado.getISBN();
        //isbnTextField.setText(isbnLivro);
        frame.add(isbnTextField);

        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloLabel.setSize(200, 20);
        tituloLabel.setLocation(575, 200);
        tituloLabel.setForeground(Color.WHITE);
        frame.add(tituloLabel);

        tituloTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        tituloTextField.setSize(200, 20);
        tituloTextField.setLocation(675, 200);
        frame.add(tituloTextField);

        autorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        autorLabel.setSize(200, 20);
        autorLabel.setLocation(575, 250);
        autorLabel.setForeground(Color.WHITE);
        frame.add(autorLabel);

        autorTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        autorTextField.setSize(200, 20);
        autorTextField.setLocation(675, 250);
        frame.add(autorTextField);

        editoraLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        editoraLabel.setSize(200, 20);
        editoraLabel.setLocation(575, 300);
        editoraLabel.setForeground(Color.WHITE);
        frame.add(editoraLabel);

        editoraTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        editoraTextField.setSize(200, 20);
        editoraTextField.setLocation(675, 300);
        frame.add(editoraTextField);

        anoPubliLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        anoPubliLabel.setSize(200, 20);
        anoPubliLabel.setLocation(500, 350);
        anoPubliLabel.setForeground(Color.WHITE);
        frame.add(anoPubliLabel);

        anoPubliTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        anoPubliTextField.setSize(200, 20);
        anoPubliTextField.setLocation(675, 350);
        frame.add(anoPubliTextField);

        generoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        generoLabel.setSize(200, 20);
        generoLabel.setLocation(575, 400);
        generoLabel.setForeground(Color.WHITE);
        frame.add(generoLabel);

        generoTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        generoTextField.setSize(200, 20);
        generoTextField.setLocation(675, 400);
        frame.add(generoTextField);

        disponibilidadeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        disponibilidadeLabel.setSize(200, 20);
        disponibilidadeLabel.setLocation(525, 450);
        disponibilidadeLabel.setForeground(Color.WHITE);
        frame.add(disponibilidadeLabel);

        disponibilidadeTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        disponibilidadeTextField.setSize(200, 20);
        disponibilidadeTextField.setLocation(675, 450);
        frame.add(disponibilidadeTextField);

        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        descricaoLabel.setSize(200, 20);
        descricaoLabel.setLocation(575, 500);
        descricaoLabel.setForeground(Color.WHITE);
        frame.add(descricaoLabel);

        descricaoTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        descricaoTextField.setSize(200, 20);
        descricaoTextField.setLocation(675, 500);
        frame.add(descricaoTextField);

        editarlivroButton.setFont(new Font("Arial", Font.PLAIN, 15));
        editarlivroButton.setSize(200, 30); // Ajustei a altura
        editarlivroButton.setLocation(675, 525); // Ajustei a posição
        //editarlivroButton.addActionListener(editarLivro());
        editarlivroButton.setForeground(Color.WHITE);
        editarlivroButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        editarlivroButton.setBackground(new Color(30, 30, 30));
        frame.add(editarlivroButton);

        frame.addWindowListener(fecharPrograma());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // NÃO FUNCIONA
    private ActionListener voltarAtras() {
        return e -> {
            frame.dispose();
            b.exibirFrame();
        };
    }

    // NÃO FUNCIONA
    private void editarLivro() {
        int indiceSelecionado = b.getIdComponenteSelecionado();
            // Pergunta de confirmação
            int opcao = JOptionPane.showConfirmDialog(frame,
                    "Deseja mesmo eliminar o livro com o título: " + livroSelecionado.getTitulo() + "?",
                    "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (opcao == JOptionPane.YES_OPTION) {
                livroSelecionado.alterarDados(livroSelecionado);
                JOptionPane.showMessageDialog(frame, "Livro eliminado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                b.refreshLivroBaseDados();
                b.exibirFrame();
                //refreshLivroBaseDados();
            } else {
                // Se nenhum livro estiver selecionado, exibe uma mensagem de aviso
                JOptionPane.showMessageDialog(frame, "Livro selecionado não existe. " +
                                "\nPor favor, selecione um livro para eliminar.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void exibirFrame() {
        // Define a frame como visível
        frame.setVisible(true);
    }

    private WindowListener fecharPrograma() {
        // Cria um WindowListener
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Chama o método para desconectar do banco de dados
                //conexao.desconectar();

                // Fecha a aplicação
                System.exit(0);
            }
        };

        // Retorna o WindowListener
        return windowListener;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditarLivroFormulario loginForm = new EditarLivroFormulario();
            loginForm.frame.setVisible(true);
        });
    }
}
