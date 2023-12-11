package org.example;

import java.sql.*;
import java.util.*;

public class Biblioteca {
    /**
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // cria scanner para interagir
        Scanner scan = new Scanner(System.in);

        Connection conexao = null;

        boolean continua = true;
        String ub; // variavel que indica quem é
        Livro livroComDados;
        Bibliotecario bibliotecario;
        Emprestimo emprestimo;
        Utilizador utilizador;

        try {
            // URL de conexão JDBC
            String url = "jdbc:mysql://localhost/biblioteca";
            String user = "ana_beatriz";
            String password = "RandomGirl2.0";

            // Estabelecer a conexão
            conexao = DriverManager.getConnection(url, user, password);

            // Criando um objetos de cada classe que já façam a conexao com a base de dados
            bibliotecario = new Bibliotecario(conexao);
            emprestimo = new Emprestimo(conexao);
            utilizador = new Utilizador(conexao);

            // Criando um objeto Livro
            livroComDados = new Livro(2, "1234567890123", "Java for Beginners", "John Doe", "Tech Publishing", 2022, "Programming", true);
            //Livro livroSemDados = new Livro();

            System.out.println("Diga quem é");
            System.out.println("Escreva 'u', se for utilizado");
            System.out.println("Escreva 'b', se for bibliotecario");
            ub = scan.nextLine();

            if ("u".equalsIgnoreCase(ub)) { // confirma se é utilizador
                /* while (continua) {
                    //System
                }*/

                // Consulta dados de um determinado livro
                utilizador.consultarLivro(1);

                // Consulta dados de todos os livros
                utilizador.consultarTodosLivros();

            } else if ("b".equalsIgnoreCase(ub)) { // confirma se é bibliotecario
                // Consulta dados dos livros
                bibliotecario.consultarTodosLivros();

                // Consulta dados de um determinado livro
                bibliotecario.consultarLivro(1);

                // Inserindo o livro no banco de dados
                bibliotecario.inserirLivro(livroComDados);
            } else {
                System.out.println("Por favor, escreva um dado válido");
            }
        
        // Fecha o scanner
        scan.close();

        // Fecha a conexão com o banco de dados
        conexao.close();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}