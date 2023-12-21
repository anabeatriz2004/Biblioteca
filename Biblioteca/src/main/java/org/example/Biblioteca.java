package org.example;

import java.sql.*;
import java.util.*;

public class Biblioteca {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // cria scanner para interagir
        Scanner scan = new Scanner(System.in);

        String ub; // variavel que indica quem é
        String email;
        String password;

        Database db = new Database();
        VerificarLogin vl = new VerificarLogin();
        Menu menu = new Menu();

        // conecta há base de dados
        db.conectar();

        // saber quem está a utilizar o programa
        System.out.println("Diga quem é:");
        System.out.println("Escreva 'u', se for utilizador");
        System.out.println("Escreva 'b', se for bibliotecario");
        System.out.println("Escreva sair, se pretender sair");
        ub = scan.nextLine();

        // inserir os dados para logar
        System.out.println("Insira os dados para efetuar o login");
        System.out.println("Escreva o seu email:");
        email = scan.nextLine();
        System.out.println("Escreva a sua password:");
        password = scan.nextLine();

        while (!(ub.equals("sair"))) {
            if ("u".equalsIgnoreCase(ub)) { // confirma se é utilizador
                vl.verificarLoginUtilizador(email, password);
                //menu.percorrerMenuUtilizador();
            } else if ("b".equalsIgnoreCase(ub)) { // confirma se é bibliotecario
                vl.verificarLoginBibliotecario(email, password);
                //umenu.percorrerMenuBibliotecario();
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