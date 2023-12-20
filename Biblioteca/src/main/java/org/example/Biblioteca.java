package org.example;

import java.sql.*;
import java.util.*;

public class Biblioteca {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // cria scanner para interagir
        Scanner scan = new Scanner(System.in);

        Connection conexao = null;

        String ub; // variavel que indica quem é

        Menu menu = new Menu();
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

            System.out.println("Diga quem é");
            System.out.println("Escreva 'u', se for utilizadorb");
            System.out.println("Escreva 'b', se for bibliotecario");
            System.out.println("Escreva sair, se pretender sair");
            ub = scan.nextLine();

            while (ub.equals("sair")) {
                if ("u".equalsIgnoreCase(ub)) { // confirma se é utilizador
                    menu.percorrerMenuUtilizador();

                } else if ("b".equalsIgnoreCase(ub)) { // confirma se é bibliotecario
                    menu.percorrerMenuBibliotecario();
                } else {
                    System.out.println("Por favor, escreva um dado válido");
                }
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