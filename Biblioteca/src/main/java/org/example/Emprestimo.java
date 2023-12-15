package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Utilizador utilizador;
    private Bibliotecario bibliotecario;
    private LocalDate dataEmprestimo,dataDevolvido,dataDevolucao;
    //private Database conexao; // Adicionando a instância da conexão

    private Connection conexao;

    // construtor sem dados
    public void Bibliotecario () {}

    // conecta-se a partir do construtor
    public Emprestimo (Connection conexao) {
        this.conexao = conexao;
    }

    Emprestimo(Livro id_livro, Utilizador id_utilizador, Bibliotecario id_bibliotecario) {
        this.livro = id_livro;
        this.utilizador = id_utilizador;
        this.bibliotecario = id_bibliotecario;
    }

    public void devolverLivro() {
        if (dataEmprestimo == null) {
            System.out.println("Este livro ainda não foi emprestado.");
            return;
        }

        dataDevolvido = LocalDate.now();
        atualizarNoBanco();
       }

    private void atualizarNoBanco() {
        try {
            String sql = "UPDATE emprestimos SET data_devolvido = ? WHERE livro_id = ? AND utilizador_id = ?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(dataDevolvido.atStartOfDay()));
            preparedStatement.setInt(2, livro.getID_livro());
            preparedStatement.setInt(3, utilizador.id_utilizador);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emprestarLivro() {
        try {
            dataEmprestimo = LocalDate.now();
            dataDevolucao = LocalDate.now().plusDays(15);

            String sql = "INSERT INTO emprestimo (id_livro, id_utilizador, id_bibliotecario, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, livro.getID_livro());
            preparedStatement.setInt(2, utilizador.id_utilizador);
            preparedStatement.setInt(3, bibliotecario.getId_bibliotecario());
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(dataEmprestimo.atStartOfDay()));
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(dataDevolucao.atStartOfDay()));

            preparedStatement.executeUpdate();

            preparedStatement.close();

            atualizar_estado_livro(false); //Atualiza a disponibilidade do livro

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar_estado_livro(boolean disponibilidade) {
        try {
            String sql = "UPDATE livro SET disponibilidade = ? WHERE id_livro = ?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setBoolean(1, disponibilidade);
            preparedStatement.setInt(2, livro.getID_livro());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
