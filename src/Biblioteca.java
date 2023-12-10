//import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Biblioteca {
    public static void main(String[] args) throws SQLException {
        // cria scanner para interagir
        //Scanner scan = new Scanner(System.in);

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "sua_senha");

            // Criando um objeto Livro
            Livro livroComDados = new Livro(2, "1234567890123", "Java for Beginners", "John Doe", "Tech Publishing", 2022, "Programming", true);
            //Livro livroSemDados = new Livro();

            // Criando um objeto Bibliotecario
            Bibliotecario bibliotecario = new Bibliotecario();

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