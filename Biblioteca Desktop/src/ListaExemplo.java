// https://www.youtube.com/watch?v=KOI1WbkKUpQ

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListaExemplo {

    JFrame frame = new JFrame("Storage");
    JList<Product> ListaExemplo = new JList<>();
    DefaultListModel<Product> ListaExemploModelo = new DefaultListModel<>();

    JTextArea textArea = new JTextArea();
    JPanel painel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    public ListaExemplo() {

        ListaExemplo.setModel(ListaExemploModelo);

        ListaExemploModelo.addElement(new Product("Laranjas", 2.0, "Larajas frescas e docinhas"));
        ListaExemploModelo.addElement(new Product("Maçãs", 1.5, "Maçãs frescas e docinhas"));
        ListaExemploModelo.addElement(new Product("Pêras", 3.0, "Pêras frescas e docinhas"));

        ListaExemplo.getSelectionModel().addListSelectionListener(e -> {
            Product p = ListaExemplo.getSelectedValue();
            textArea.setText("Nome: " + p.getNome() +
                    "\nPreco: " + p.getPreco().toString() +
                    "\nDescricao: " + p.getDescricao());
        });

        splitPane.setLeftComponent(new JScrollPane(ListaExemplo));
        painel.add(textArea);
        splitPane.setRightComponent(painel);

        // feche a frame, mas a aplicação continua em funcionamento
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private class Product {
        String nome; //name
        Double preco; //price
        String descricao; // desc

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public Double getPreco() {
            return preco;
        }
        public void setPreco(Double preco) {
            this.preco = preco;
        }
        public String getDescricao() {
            return descricao;
        }
        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public Product (String nome, Double preco, String descricao) {
            this.nome = nome;
            this.preco = preco;
            this.descricao = descricao;
        }

        @Override
        public String toString() {
            //return "Product{" + "nome='" + nome + '\'' + ", preco=" + preco + ", descricao='" + descricao + '\'' + '}';
            return nome;
        }
    }

    public static void main(String[] args) {
        // inicia a aplicação
        SwingUtilities.invokeLater(ListaExemplo::new);
    }
}


