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
            System.out.println("Escreva 'u', se for utilizador");
            System.out.println("Escreva 'b', se for bibliotecario");
            System.out.println("Escreva sair, se pretender sair");
            ub = scan.nextLine();

            String email = "usuario@exemplo.com";
            String senhaInformada = "senha123";

            try {
                Statement stmt = conexao.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM utilizador WHERE email = '" + email + "'");


                while (!(ub.equals("sair"))) {
                    if ("u".equalsIgnoreCase(ub)) { // confirma se é utilizador

                        if (rs.next()) {
                            String senhaBanco = rs.getString("password");

                            if (senhaBanco.equals(senhaInformada)) {
                                System.out.println("Usuário logado com sucesso!");
                                menu.percorrerMenuUtilizador();
                            } else {
                                System.out.println("Senha incorreta!");
                            }
                        } else {
                            System.out.println("Usuário não encontrado. Inscreva-se!");
                        }

                    } else if ("b".equalsIgnoreCase(ub)) {
                        if (rs.next()) {
                            String senhaBanco = rs.getString("password");

                            if (senhaBanco.equals(senhaInformada)) {
                                System.out.println("Usuário logado com sucesso!");
                            } else {
                                System.out.println("Senha incorreta!");
                            }
                        } else {
                            System.out.println("Usuário não encontrado. Inscreva-se!");
                        }// confirma se é bibliotecario
                        menu.percorrerMenuBibliotecario();
                    } else {
                        System.out.println("Por favor, escreva um dado válido");
                    }
                }

            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }

            // Fecha o scanner
            scan.close();

            // Fecha a conexão com o banco de dados
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}