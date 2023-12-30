import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Tabela extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;

    Tabela() {
        // Defina o título
        super("Tabela Demo");

        // Crie o modelo da tabela
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Contas", "Montante"});

        // Crie a tabela com o modelo
        tabela = new JTable(model);

        // Adicione a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicione o JScrollPane à frame
        add(scrollPane);

        // Defina o modo de fechamento
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Defina o tamanho certo da frame
        pack();

        // Para exibir no centro da tela
        setLocationRelativeTo(null);

        // Carregue os dados na tabela
        loadData();

        // Para visualizar
        setVisible(true);
    }

    private void loadData() {
        try {
            // Obtenha o caminho do arquivo
            String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            // Leia o arquivo com dados
            BufferedReader br = new BufferedReader(new FileReader(path + "/tabela.txt"));

            // Armazene os dados em um ArrayList
            ArrayList<Object[]> dataList = new ArrayList<>();

            // Leia cada linha do buffer
            String linha;
            while ((linha = br.readLine()) != null) {
                // Separe os dados por vírgula
                String[] dados = linha.split(",");
                // Adicione os dados ao ArrayList
                dataList.add(dados);
            }

            // Feche o buffer
            br.close();

            // Converta o ArrayList para um array bidimensional
            Object[][] dadosArray = new Object[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                dadosArray[i] = dataList.get(i);
            }

            // Adicione os dados ao modelo da tabela
            for (Object[] row : dadosArray) {
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Inicie a aplicação
        new Tabela();
    }
}
