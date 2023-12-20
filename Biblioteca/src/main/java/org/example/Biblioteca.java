package org.example;

import java.sql.*;
import java.util.*;

public class Biblioteca {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // cria scanner para interagir
        Scanner scan = new Scanner(System.in);

        String ub; // variavel que indica quem é

        Menu menu = new Menu();
        Database db = new Database();

        // conecta há base de dados
        db.conectar();

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
        db.desconectar();
    }
}