// https://www.youtube.com/watch?v=KOI1WbkKUpQ

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class Lista {

    Livro l = new Livro();

    JFrame frame = new JFrame("Biblioteca");
    JList<Livro> lista = new JList<>();
    DefaultListModel<Livro> listaModelo = new DefaultListModel<>();

    JTextArea textArea = new JTextArea();
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    public Lista() {

        lista.setModel(listaModelo);

        ArrayList<Livro> todosOsLivros = l.consultarTodosLivros();

        for (int i = 0; i < todosOsLivros.size(); i++) {
            Livro livro = todosOsLivros.get(i);
            listaModelo.addElement(livro);
        }

        lista.setCellRenderer(new LivroRenderer());

        lista.getSelectionModel().addListSelectionListener(e -> {
            Livro livro = lista.getSelectedValue();
            textArea.setText("ISBN: " + livro.getISBN() +
                    "\nTitulo: " + livro.getTitulo() +
                    "\nAutor: " + livro.getAutor() +
                    "\nEditora: " + livro.getEditora() +
                    "\nAno de Publicação: " + livro.getAnoPubli() +
                    "\nGênero: " + livro.getGenero() +
                    "\nDisponibilidade: " + livro.isDisponibilidade());
        });

        splitPane.setLeftComponent(new JScrollPane(lista));
        painel.add(textArea);
        splitPane.setRightComponent(painel);

        // feche a frame, mas a aplicação continua em funcionamento
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    // Renderizador customizado para exibir apenas o título
    private class LivroRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Livro livro = (Livro) value;
            setText(livro.getTitulo());
            return this;
        }
    }

    public static void main(String[] args) {
        // inicia a aplicação
        SwingUtilities.invokeLater(Lista::new);
    }
}

