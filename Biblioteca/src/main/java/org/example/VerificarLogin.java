package org.example;

import java.sql.*;

public class VerificarLogin {
    private Database conexao;
    Menu menu = new Menu();

    // Método para verificar o login do utilizador
    public String verificarLoginUtilizador(String email, String password) {
        conexao.getConexao();

        System.out.println("verificando o login do utilizador...");

        try {
            // Define a consulta SQL para selecionar um livro com base no ID
            String sql = "SELECT * FROM utilizador WHERE email = ?";

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
                System.out.println("verificando o login do utilizador...");
                // Define o parâmetro da consulta com base no ID fornecido
                pstmt.setString(1, email);

                // Executa a consulta
                ResultSet resultSet = pstmt.executeQuery();

                // Verifica se o email foi encontrado
                if (resultSet.next()) {
                    // Email encontrado, verifica a senha
                    String senhaDoBanco = resultSet.getString("password");

                    if (senhaDoBanco.equals(password)) {
                        // Senha correta, login bem-sucedido
                        menu.percorrerMenuUtilizador();
                        return "O utilizador logou com sucesso";
                    } else {
                        // Senha incorreta, pede para tentar novamente
                        return "Password incorreta, por favor, tente novamente";
                    }
                } else {
                    // Email não encontrado
                    return "Utilizador não encontrado";
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao conectar ao banco de dados";
        }
    }

    // Método para verificar o login do bibliotecario
    public String verificarLoginBibliotecario(String email, String password) {
        conexao.conectar(); // Conectando ao banco usando a instância de ConexaoMySQL

        System.out.println("verificando o login do utilizador...");

        try {
            // Define a consulta SQL para selecionar um livro com base no ID
            String sql = "SELECT * FROM bibliotecario WHERE email = ?";

            System.out.println("verificando o login do utilizador...");

            // Cria o objeto PreparedStatement
            try (PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql)) {
                System.out.println("verificando o login do utilizador...");
                // Define o parâmetro da consulta com base no ID fornecido
                pstmt.setString(1, email);

                // Executa a consulta
                ResultSet resultSet = pstmt.executeQuery();

                // Verifica se o email foi encontrado
                if (resultSet.next()) {
                    // Email encontrado, verifica a senha
                    String senhaDoBanco = resultSet.getString("password");

                    if (senhaDoBanco.equals(password)) {
                        // Senha correta, login bem-sucedido
                        menu.percorrerMenuBibliotecario();
                        return "O utilizador logou com sucesso";
                    } else {
                        // Senha incorreta, pede para tentar novamente
                        return "Password incorreta, por favor, tente novamente";
                    }
                } else {
                    // Email não encontrado
                    return "Utilizador não encontrado";
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao conectar ao banco de dados";
        }
    }
}
