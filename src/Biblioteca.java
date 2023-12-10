//import java.sql.*;

import java.sql.*;

public class Biblioteca {
    /**
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // cria scanner para interagir
        //Scanner scan = new Scanner(System.in);

        try {
            // URL de conexão JDBC
            String url = "jdbc:mysql://127.0.0.1:3306/biblioteca";
            String utilizador = "root";
            String senha = "";  // Senha vazia, pois você mencionou que não tem senha

            // Estabelecer a conexão
            Connection conexao = DriverManager.getConnection(url, utilizador, senha);

            // Criando um objetos de cada classe que já façam a conexao com a base de dados
            Bibliotecario bibliotecario = new Bibliotecario(conexao);
            Emprestimo emprestimo = new Emprestimo(conexao);
            Utilizador utilizador = new Utilizador(conexao);

            // Criando um objeto Livro
            Livro livroComDados = new Livro(2, "1234567890123", "Java for Beginners", "John Doe", "Tech Publishing", 2022, "Programming", true);
            //Livro livroSemDados = new Livro();


            // variavel que indica quem é
            /*String ub;

            System.out.println("Diga quem é");
            System.out.println("Escreva 'u', se for utilizado");
            System.out.println("Escreva 'b', se for bibliotecario");
            ub = scan.nextLine();

            if ("u".equalsIgnoreCase(ub)) { // confirma se é utilizador

            } else if ("b".equalsIgnoreCase(ub)) { // confirma se é bibliotecario
                // Inserindo o livro no banco de dados
                bibliotecario.inserirLivro(livroComDados);
            } else {
                System.out.println("Por favor, escreva um dado válido");
            }*/

            // Inserindo o livro no banco de dados
            bibliotecario.inserirLivro(livroComDados);
        
        // Fecha a conexão com o banco de dados
        conexao.close();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}