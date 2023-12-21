package org.example;

import java.sql.*;
import java.util.Scanner;

public class VerificarLogin {
    // private Database conexao;
    private Database conexao = new Database(); // Instanciar a classe Database
    Menu menu = new Menu();

    static Scanner scan = new Scanner(System.in);
    boolean entradaValida = false;

    public void VerificarLogin() {}

    // Método para verificar o login do utilizador
    public void verificarLoginUtilizador(String email, String password) {
        try {
            // Define a consulta SQL para selecionar um livro com base no ID
            String sql = "SELECT * FROM utilizador WHERE email = ?";

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
                // Define o parâmetro da consulta com base no ID fornecido
                pstmt.setString(1, email);

                // Executa a consulta
                ResultSet resultSet = pstmt.executeQuery();

                //while (!entradaValida) {
                    // Verifica se o email foi encontrado
                    if (resultSet.next()) {
                        // Email encontrado, verifica a senha
                        String pass = resultSet.getString("senha");
                        if (pass.equals(password)) {
                            // Senha correta, login bem-sucedido
                            menu.percorrerMenuUtilizador();
                            entradaValida = true;
                            System.out.println("O utilizador logou com sucesso");
                        } else {
                            // Senha incorreta, pede para tentar novamente
                            entradaValida = false;
                            System.out.println("Password incorreta, por favor, tente novamente");
                        }
                    } else {
                        // Email não encontrado
                        entradaValida = false;
                        System.out.println("Utilizador não encontrado");
                    }
                //}
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
        //return "";
    }

    // Método para verificar o login do bibliotecario
    public void verificarLoginBibliotecario(String email, String password) {

        try {
            // Define a consulta SQL para selecionar um livro com base no ID
            String sql = "SELECT * FROM bibliotecario WHERE email = ?";

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
                // Define o parâmetro da consulta com base no ID fornecido
                pstmt.setString(1, email);

                // Executa a consulta
                ResultSet resultSet = pstmt.executeQuery();

                while (!entradaValida) {
                    // Verifica se o email foi encontrado
                    if (resultSet.next()) {
                        // Email encontrado, verifica a senha
                        String pass = resultSet.getString("senha");
                        if (pass.equals(password)) {
                            // Senha correta, login bem-sucedido
                            menu.percorrerMenuBibliotecario();
                            entradaValida = true;
                            System.out.println("O bibliotecario logou com sucesso");
                        } else {
                            // Senha incorreta, pede para tentar novamente
                            entradaValida = false;
                            System.out.println("Password incorreta, por favor, tente novamente");
                        }
                    } else {
                        // Email não encontrado
                        entradaValida = false;
                        System.out.println("Bibliotecario não encontrado");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}
