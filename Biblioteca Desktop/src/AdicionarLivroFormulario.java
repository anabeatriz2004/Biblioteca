import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

/** A classe AdicionarLivroFormulario representa um formulário para adicionar um novo livro. */
public class AdicionarLivroFormulario {
    Connection conexao = Database.getConexao(); // conecta-se à da base de dados
    Bibliotecario b = new Bibliotecario();

    JFrame frame;
    public JButton voltarBotao = new JButton("<-- Voltar"); // botão para voltar

    public final JLabel tituloInicialLabel = new JLabel("Adicionar Livro"); // título do programa

    // componenetes da frame sobre o isbn
    public final JLabel isbnLabel = new JLabel("ISBN: ");
    public JTextField isbnTextField = new JTextField();
    public final JLabel isbnErroLabel = new JLabel("ISBN sem dados!");

    // componenetes da frame sobre o título
    public final JLabel tituloLabel = new JLabel("Título: ");
    public JTextField tituloTextField = new JTextField();
    public final JLabel tituloErroLabel = new JLabel("Título sem dados!");

    // componentes da frame sobre o autor
    public final JLabel autorLabel = new JLabel("Autor: ");
    public JTextField autorTextField = new JTextField();
    public final JLabel autorErroLabel = new JLabel("Autor sem dados!");

    // componentes da frame sobre a editora
    public final JLabel editoraLabel = new JLabel("Editora: ");
    public JTextField editoraTextField = new JTextField();
    public final JLabel editoraErroLabel = new JLabel("Editora sem dados!");

    // componenetes da frame sobre o ano de publicação
    public final JLabel anoPubliLabel = new JLabel("Ano de Publicação: ");
    public JTextField anoPubliTextField = new JTextField();
    public final JLabel anoPubliErroLabel = new JLabel("Ano de Publicação sem dados!");

    // componenetes da frame sobre o gênero
    public final JLabel generoLabel = new JLabel("Gênero: ");
    public JTextField generoTextField = new JTextField();
    public final JLabel generoErroLabel = new JLabel("Gênero sem dados!");

    // componenetes da frame sobre a dispobilidade
    public final JLabel disponibilidadeLabel = new JLabel("Disponibilidade: ");
    public JComboBox<String> disponibilidadeComboBox = new JComboBox<>();
    public final JLabel disponibilidadeErroLabel = new JLabel("Disponibilidade sem dados!");

    // componenetes da frame sobre o descrição
    public final JLabel descricaoLabel = new JLabel("Descricao: ");
    public JTextArea descricaoTextArea = new JTextArea();
    public final JLabel descricaoErroLabel = new JLabel("Descricao sem dados!");

    // botão para adicionar livro
    public JButton adicionarLivroBotao = new JButton("Adicionar Livro");

    // construtor simples e vazio
    AdicionarLivroFormulario() {}

    /** Método para exibir a frame do formulário para inserir livros na base de dados */
    public void exibirFrame() {
        // Código para personalizar a frame
        frame = new JFrame("Formulário - Adicionar Livro"); // cria e coloca o nome da frame
        frame.setLayout(null); // não tem layout definido
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // descobre o tamanho do ecrã
        frame.setSize(screenSize); // define o tamanho do ecrã
        frame.getContentPane().setBackground(Color.BLACK); // coloca a cor de fundo a preto

        // Código para personalizar a voltao, para voltar
        voltarBotao.setFont(new Font("Arial", Font.PLAIN, 12)); // seleciona a fonte da letra e o tamanho
        voltarBotao.setSize(110, 30); // seleciona o teamnho do botão
        voltarBotao.setLocation(0, 0); // seleciona o lugar, onde ficará posicionado
        voltarBotao.setForeground(Color.white); // coloca a cor de texto a branco
        voltarBotao.setBorder(BorderFactory.createLineBorder(Color.white, 2)); // coloca a borda do botão a branco
        voltarBotao.setBackground(new Color(30, 30, 30)); // coloca a cor de fundo a branco
        voltarBotao.addActionListener(voltarAtras()); //Código para ir buscar a ação do botão
        frame.add(voltarBotao); // adiciona há frame

        // Código para personalizar o título
        tituloInicialLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        tituloInicialLabel.setSize(200, 100);
        tituloInicialLabel.setLocation(650, 50);
        tituloInicialLabel.setForeground(Color.white);
        frame.add(tituloInicialLabel); // adiciona há frame

        // Código para personalizar o texto da label do isbn
        isbnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnLabel.setSize(200, 20);
        isbnLabel.setLocation(100, 150);
        isbnLabel.setForeground(Color.white);
        frame.add(isbnLabel); // adiciona há frame

        // Código para personalizar o lugar onde será exibido os erros
        isbnErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnErroLabel.setSize(1100, 20);
        isbnErroLabel.setLocation(200, 175);
        isbnErroLabel.setForeground(Color.white);
        frame.add(isbnErroLabel); // adiciona há frame

        // Código para personalizar o campo de texto, neste caso do isbn
        isbnTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        isbnTextField.setSize(1100, 20);
        isbnTextField.setLocation(200, 150);
        frame.add(isbnTextField); // adiciona há frame

        // Código para personalizar a label do título
        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloLabel.setSize(200, 20);
        tituloLabel.setLocation(100, 200);
        tituloLabel.setForeground(Color.white);
        frame.add(tituloLabel); // adiciona há frame

        // Código para personalizar o lugar onde aparecerá os erros do título
        tituloErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloErroLabel.setSize(1100, 20);
        tituloErroLabel.setLocation(200, 225);
        tituloErroLabel.setForeground(Color.white);
        frame.add(tituloErroLabel); // adiciona há frame

        // Código para personalizar o campo de texto do título
        tituloTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        tituloTextField.setSize(1100, 20);
        tituloTextField.setLocation(200, 200);
        frame.add(tituloTextField); // adiciona há frame

        // Código para personalizar a label so autor
        autorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        autorLabel.setSize(200, 20);
        autorLabel.setLocation(100, 250);
        autorLabel.setForeground(Color.white);
        frame.add(autorLabel); // adiciona há frame

        // Código para personalizar o lugar onde aparecerá os erros do título
        autorErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        autorErroLabel.setSize(1100, 20);
        autorErroLabel.setLocation(200, 275);
        autorErroLabel.setForeground(Color.white);
        frame.add(autorErroLabel); // adiciona há frame

        // Código para personalizar o campo de texto do autor
        autorTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        autorTextField.setSize(1100, 20);
        autorTextField.setLocation(200, 250);
        frame.add(autorTextField); // adiciona há frame

        // Código para personalizar a label da editora
        editoraLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraLabel.setSize(200, 20);
        editoraLabel.setLocation(100, 300);
        editoraLabel.setForeground(Color.white);
        frame.add(editoraLabel); // adiciona há frame

        // Código para personalizar onde aparecerá os erros da editora
        editoraErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraErroLabel.setSize(1100, 20);
        editoraErroLabel.setLocation(200, 325);
        editoraErroLabel.setForeground(Color.white);
        frame.add(editoraErroLabel);  // adiciona há frame

        // Código opara personalizar o campo de texto da editora
        editoraTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        editoraTextField.setSize(1100, 20);
        editoraTextField.setLocation(200, 300);
        frame.add(editoraTextField); // adiciona há frame

        // Código para personalizar a label do ano de publicação
        anoPubliLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliLabel.setSize(200, 20);
        anoPubliLabel.setLocation(100, 350);
        anoPubliLabel.setForeground(Color.white);
        frame.add(anoPubliLabel); // adiciona há frame

        // Código para personailzar onde apareceram os erros do ano de publicação
        anoPubliErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliErroLabel.setSize(1100, 20);
        anoPubliErroLabel.setLocation(200, 375);
        anoPubliErroLabel.setForeground(Color.white);
        frame.add(anoPubliErroLabel); // adiciona há frame

        // Código para personalizar o campo de texto de ano da publicação
        anoPubliTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        anoPubliTextField.setSize(1100, 20);
        anoPubliTextField.setLocation(200, 350);
        frame.add(anoPubliTextField);  // adiciona há frame

        // Código para personalizar a label do gênero do livro
        generoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        generoLabel.setSize(200, 20);
        generoLabel.setLocation(100, 400);
        generoLabel.setForeground(Color.white);
        frame.add(generoLabel); // adiciona há frame

        // Código para personalizar onde apareerá os erros do gênero do livro
        generoErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        generoErroLabel.setSize(1100, 20);
        generoErroLabel.setLocation(200, 425);
        generoErroLabel.setForeground(Color.white);
        frame.add(generoErroLabel); // adiciona há frame

        // Código para personalizar o campo de texto do gênero
        generoTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        generoTextField.setSize(1100, 20);
        generoTextField.setLocation(200, 400);
        frame.add(generoTextField); // adiciona há frame

        // Código para personalizar a label da dispobilidade
        disponibilidadeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeLabel.setSize(200, 20);
        disponibilidadeLabel.setLocation(100, 450);
        disponibilidadeLabel.setForeground(Color.white);
        frame.add(disponibilidadeLabel); // adiciona há frame

        // Código para personalizar onde aparecerá os erros da disponibilidade
        disponibilidadeErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeErroLabel.setSize(1100, 20);
        disponibilidadeErroLabel.setLocation(200, 475);
        disponibilidadeErroLabel.setForeground(Color.white);
        frame.add(disponibilidadeErroLabel); // adiciona há frame

        // Código para personalizar a caixa de combinaçãoo da disponibilidade
        disponibilidadeComboBox.addItem("Disponível"); // adiciona há caixa de combinação o item (se fosse do tipo boolean, seria true)
        disponibilidadeComboBox.addItem("Emprestado"); // adiciona há caixa de combinação o item (se fosse do tipo boolean, seria false)
        disponibilidadeComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        disponibilidadeComboBox.setSize(1100, 20);
        disponibilidadeComboBox.setLocation(200, 450);
        frame.add(disponibilidadeComboBox); // adiciona há frame

        // Código para personalizar a label da descricao
        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoLabel.setSize(200, 20);
        descricaoLabel.setLocation(100, 500);
        descricaoLabel.setForeground(Color.white);
        frame.add(descricaoLabel); // adiciona há frame

        // Código para personalizar onde apareceram os erros da descrição
        descricaoErroLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoErroLabel.setSize(1100, 20);
        descricaoErroLabel.setLocation(200, 600);
        descricaoErroLabel.setForeground(Color.white);
        frame.add(descricaoErroLabel); // adiciona há frame

        // Código para personalizar a caixa de texto de descrição
        descricaoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoTextArea.setSize(1100, 100);
        descricaoTextArea.setLocation(200, 500);
        frame.add(descricaoTextArea); // adiciona há frame

        // Código para personalizar o botão para adicionar livro
        adicionarLivroBotao.setFont(new Font("Arial", Font.PLAIN, 20));
        adicionarLivroBotao.setSize(200, 30);
        adicionarLivroBotao.setLocation(650, 615);
        adicionarLivroBotao.setForeground(Color.white);
        adicionarLivroBotao.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        adicionarLivroBotao.setBackground(new Color(30, 30, 30));
        adicionarLivroBotao.addActionListener(e -> {
            adicionarLivro();
        });
        frame.add(adicionarLivroBotao); // adiciona há frame

        frame.addWindowListener(fecharPrograma()); // Adiciona um WindowListener para lidar com eventos de fechamento da janela
        frame.setResizable(false); // a janela não é redimensionável
        frame.setVisible(true); // a janela é visível
    }

    /** Método que apaga a frame atual e volta para a frame da classe Bibliotecário*/
    private ActionListener voltarAtras() {
        return e -> {
            frame.dispose(); // feche a janela
            b.exibirFrame(); // exibe a frame do bibliotecário
        };
    }

    /** Método utilizado após clicar no botão adicionarLivroBotao, recolhe os dados dos campos de texto,
     * e verfica se estão a ser colocados corretamente antes de ser inseridos na base de dados */
    public Livro verificarDados() {
        // componentes para a validação dos dados, de início estão a falso,
        // pois não podem ser inseridos na base de dados
        boolean dadoValidoIsbn;
        boolean dadoValidoTitulo;
        boolean dadoValidoAutor;
        boolean dadoValidoEditora;
        boolean dadoValidoAnoPubli;
        boolean dadoValidoGenero;
        boolean dadoValidoDisponibilidade = false;
        boolean dadoValidoDescricao;

        // recolhe os dados do campos de texto
        String isbnStr = isbnTextField.getText();
        String titulo = tituloTextField.getText();
        String autor = autorTextField.getText();
        String editora = editoraTextField.getText();
        String anoPubliStr = anoPubliTextField.getText();
        String genero = generoTextField.getText();
        String disponibilidadeStr = (String) disponibilidadeComboBox.getSelectedItem(); // recolhe o dado selecionado
        String descricao = descricaoTextArea.getText();

        // criação de componentes de acordo como serão inseridos na base de dados
        int anoPubli = 0;
        boolean disponibilidade = true;

        // verifica se o isbn é inserido corretamente
        if (isbnStr.isEmpty()) { // verifica se não contém dados
            isbnErroLabel.setText("Isbn sem dados!"); // avisa que está vazio
            isbnErroLabel.setForeground(Color.yellow); // coloca o texto a amarelo
            dadoValidoIsbn = true; // dado válido, pode ser inserido
        } else if (isbnStr.matches("\\d{13}")) { // verifica se têm 13 números
            isbnErroLabel.setText("ISBN inserido corretamente!"); // avisa que é inserido corretamente
            isbnErroLabel.setForeground(Color.green); // coloca o texto a verde
            dadoValidoIsbn = true; // dado válido, pode ser inserido
        } else {
            if (isbnStr.matches(".*\\D.*")) { // caso contrário, verifica se tem dados não númericos
                isbnErroLabel.setText("O ISBN só pode conter números!"); // avisa que só pode ter letras
            } else {
                isbnErroLabel.setText("Confirme se tem 13 números!"); // caso contrário, diz para confirmar se têm realmente 13 dígitos
            }
            isbnErroLabel.setForeground(Color.red); // coloca o texto a vermelho
            dadoValidoIsbn = false; // dado inválido, não pode ser inserido
        }

        // verifica se o título é inserido corretamente
        if (titulo.isEmpty()) { // verifica se não contém dados
            tituloErroLabel.setText("Título sem dados!"); // afirma que está sem dados
            tituloErroLabel.setForeground(Color.yellow); // apresenta texto a amarelo
            dadoValidoTitulo = true; // dado válido, pode ser inserido
        } else { // caso contrário, verifica se contém dados
            tituloErroLabel.setText("Título inserido corretamente!"); // afirma que fora inserido corretamente
            tituloErroLabel.setForeground(Color.green); // coloca o texto a verde
            dadoValidoTitulo = true; // dado válido, pode ser inserido
        }

        // verifica se o autor é inserido corretamente
        if (autor.isEmpty()) { // verifica se não contém dados
            autorErroLabel.setText("Autor sem dados!"); // avisa que está sem dados
            autorErroLabel.setForeground(Color.yellow); // coloca o texto a amarelo
            dadoValidoAutor = true; // dado válido, pode ser inserido
        } else { // caso contrário, verifica se contém dados
            autorErroLabel.setText("Autor inserido corretamente!"); // avisa que foi inserido corretamente
            autorErroLabel.setForeground(Color.green); // coloca o texto a verde
            dadoValidoAutor = true; // dado válido, pode ser inserido
        }

        // verifica se a editora é inserida corretamente
        if (editora.isEmpty()) { // verifica se a editora é inserida corretamente
            editoraErroLabel.setText("Editora sem dados!"); // avisa que não contém dados
            editoraErroLabel.setForeground(Color.yellow); // coloca o texto amarelo
            dadoValidoEditora = true; // dado válido, pde ser inserido
        } else { // caso contrário, verifica se contém dados
            editoraErroLabel.setText("Editora inserido corretamente!"); // avisa que foi inserido corretamente
            editoraErroLabel.setForeground(Color.green); // coloca o texto a verde
            dadoValidoEditora = true; // dado válido, pode ser inserido
        }

        // Vê se o anoPubli é inserido corretamente
        if (anoPubliStr.isEmpty()) { // verifica se contém dados
            anoPubliErroLabel.setText("Ano de publicação sem dados!"); // verifica se está sem dados
            anoPubliErroLabel.setForeground(Color.yellow); // coloca o texto a amarelo
            dadoValidoAnoPubli = true;
        } else { // caso contrário...
            try {
                // Tenta converter a string anoPubliStr para um número inteiro
                anoPubli = Integer.parseInt(anoPubliStr);

                // Verificar se está dentro de um intervalo específico (por exemplo, 0 a 2024)
                if (anoPubli >= 0 && anoPubli <= 2024) {
                    anoPubliErroLabel.setText("Ano de publicação inserido corretamente!"); // avisa que foi inserido corretamente
                    anoPubliErroLabel.setForeground(Color.green); // coloca o texto a verde
                    dadoValidoAnoPubli = true; // dado válido pode ser inserido
                } else { // caso não estiver, dentro do intervalo especificado
                    anoPubliErroLabel.setText("O ano de publicação deve estar entre 0 e 2024!"); // avisa que deve está entre o intervalo especificado
                    anoPubliErroLabel.setForeground(Color.red); // coloca o texto a vermelho
                    dadoValidoAnoPubli = false; // dado inválido não pode ser inserido
                }
            } catch (NumberFormatException e) { // se caso ocorrer um erro em tentar tornar uma string em um número
                anoPubliErroLabel.setText("Confirme se inseriu um número válido para o ano de publicação!"); // avisa que inseriu uma string
                anoPubliErroLabel.setForeground(Color.red); // coloca o texto a vermelho
                dadoValidoAnoPubli = false; // dado inválido, não pode ser inserido
            }
        }

        // verifica se o gênero é inserido corretamente
        if (genero.isEmpty()) { // verifica se o gênero está vazio
            generoErroLabel.setText("Gênero sem dados!"); // avisa que não tem dados
            generoErroLabel.setForeground(Color.yellow); // coloca o texto a amarelo
            dadoValidoGenero = true; // dado válido, pode ser inserido
        } else { // caso contrário...
            generoErroLabel.setText("Gênero inserido corretamente!"); // gênero inserido corretamente
            generoErroLabel.setForeground(Color.green); // coloca o texto a verde
            dadoValidoGenero = true; // dado válido, pode ser inserido
        }

        // verifica se o estado da disponibilidade
        if (disponibilidadeStr.equals("Disponível")) { // verifica se está disponíveç
            disponibilidadeErroLabel.setText("Declarou que o livro se encontra disponivel."); // declara que o livro está disponível
            disponibilidadeErroLabel.setForeground(Color.green); // coloca o texto a verde
            disponibilidade = true; // afirma que está disponível
            dadoValidoDisponibilidade = true; // dado válido, pode ser inserido
        } else if (disponibilidadeStr.equals("Emprestado")) { // verifica se está emprestado
            disponibilidadeErroLabel.setText("Declarou que o livro foi emprestado."); // declara que o livro foi emprestado
            disponibilidadeErroLabel.setForeground(Color.green); // coloca o texto a verde
            disponibilidade = false; // afirma que já fora emprestado
            dadoValidoDisponibilidade = true; // dado válido, pode ser inserido
        }

        // verifica se a descrição foi incerido corretamente
        if (descricao.isEmpty()) { // verifica se a descrição está vazia
            descricaoErroLabel.setText("Gênero sem dados!"); // avisa que não tem dados
            descricaoErroLabel.setForeground(Color.yellow); // coloca o texto a amarelo
            dadoValidoDescricao = true; // dado válido, pode ser inserido
        } else { // caso contrário...
            descricaoErroLabel.setText("Gênero inserido corretamente!"); // descrição inserido corretamente
            descricaoErroLabel.setForeground(Color.green); // coloca o texto a verde
            dadoValidoDescricao = true; // dado válido, pode ser inserido
        }

        // Verifica se todos os dados são válidos
        if (dadoValidoIsbn && dadoValidoTitulo && dadoValidoAutor && dadoValidoEditora && dadoValidoAnoPubli && dadoValidoGenero && dadoValidoDisponibilidade && dadoValidoDescricao) {
            Livro livro = new Livro(isbnStr, titulo, autor, editora, anoPubli, genero, disponibilidade, descricao);
            return livro; // retorna o livro
        } else {
            // Se algum dado não for válido, não permita a criação do livro e retorne null
            return null;
        }
    }

    /** Método para adicionar livros há base de dados*/
    public void adicionarLivro() {
        // O livroAdicionado, guarda os dados dos livros, após serem validados
        Livro livroAdicionado = verificarDados();
        // chama o método para inserir livros na base de dados
        livroAdicionado.inserirLivro(livroAdicionado);
        frame.dispose(); // feche a janela atual
        b.exibirFrame(); // mostra a janela do bibliotecário
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
