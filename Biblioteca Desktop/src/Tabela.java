import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Tabela extends JFrame{
    JFrame frame; //jf
    private JPanel TabelaPanel;
    private JTable tabela; //jt
    JScrollPane scrollPane; //js
    String[] coluna; // col
    Object[][] dados; // data

    Tabela () {
        // titulo
        frame = new JFrame("Tabela Demo");
        // definir a matriz da tabela, definindo o nome das colunas
        coluna = new String[]{"Contas", "Montante"};
        // definir o array
        dados = getData();
        // criar a tabela, passando os dados e o nome das colunas
        tabela = new JTable(dados, coluna);
        // adicionar o scroll da tabela
        scrollPane = new JScrollPane(tabela);
        // adicionar há frame
        frame.add(scrollPane);
        // definir o modo de fechamento
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // definir o tamanho certo da frame
        frame.pack();
        // para exibir no centro da tela
        setLocationRelativeTo(null);
        // para vizualizar
        setVisible(true);
    }

    Object[][] getData() {
        try {
            // vai buscar o caminho do ficheiro
            String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            // ler arquivo com dados
            BufferedReader br = new BufferedReader(new FileReader(path + "/tabela.txt"));
            // armazena os dados num array
            ArrayList <String> array = new ArrayList();
            // para ler cada linha do buffer
            String texto = "";

            // loop para a leitura de arquivo
            // usar o readLine() para ler linha a linha
            // se caso a linha não for nula, continua a fazer o loop
            while ((texto = br.readLine()) != null)  {
                // adicionar cada string ao nosso array
                array.add(texto);
                // para saber se lê corretamente
                System.out.println(texto);
            }

            //saber o tamanho dos dados a partir da quantidade de vírgulas no ficheiro
            int n = array.get(0).split(",").length;

            //definir o tamanho do array
            Object[][] dados = new Object[array.size()][n];

            // loop para preencher o array
            for (int i = 0; i < array.size(); i++) {
                // preenche o array e separa os por vírgula
                dados[i] = array.get(i).split(",");
            }

            //fechar o buffer
            br.close();

            // confirmar se dados, tem valores guardados
            System.out.println(Arrays.deepToString(dados));
            // returnar o array
            return dados;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        // inicia a aplicação
        new Tabela();
    }
}
