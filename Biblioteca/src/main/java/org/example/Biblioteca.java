package org.example;

import java.util.*;

public class Biblioteca {
    // cria scanner para interagir
    static Scanner scan = new Scanner(System.in);

    /*public void EncerrarPrograma {
        System.out.println("O programa está prestes a encerrar.");

        // Fechar o programa com status de saída 0 (sem erros)
        System.exit(0);
        conexao.desconectar();
        scan.close();

        // O código abaixo não será executado, pois o programa já foi encerrado
        System.out.println("Esta mensagem não será exibida.");
    }*/

    public static void main(String[] args) {

        String ub; // variavel que indica quem é
        String email;
        String password;

        VerificarLogin vl = new VerificarLogin();
        Menu menu = new Menu();

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
                //menu.percorrerMenuBibliotecario();
            } else {
                System.out.println("Por favor, escreva um dado válido");
            }
            scan.close();
        }
    }
}