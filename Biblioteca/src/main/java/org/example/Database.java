package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    //private static final String URL = "jdbc:mysql://seu_host:porta/seu_banco";
    //private static final String USUARIO = "seu_usuario";
    //private static final String SENHA = "sua_senha";

    private static final String url = "estga-dev.ua.pt/PTDA_BD_005";
    private static final String user = "PTDA_005";
    private static final String password = "Zh#pos539";

    private Connection conexao = null;

    public Database() {
        conectar();
    }

    public Connection getConexao() {
        return conexao;
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem-sucedida.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão encerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
