package org.example;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    Utilizador utilizador;
    Bibliotecario bibliotecario;

    // Criando um objeto Livro
    Livro livroComDados = new Livro(2, "1234567890123", "Java for Beginners", "John Doe", "Tech Publishing", 2022, "Programming", true);

    int op;

    public static void exibirMenuUtilizador() {
        System.out.println("----- Menu -----");
        System.out.println("1. Opção 1");
        System.out.println("2. Opção 2");
        System.out.println("3. Opção 3");
        System.out.println("4. Sair");
        System.out.println("-----------------");
    }

    public void percorrerMenuUtilizador() {
        do {
            exibirMenuUtilizador();
            System.out.print("op uma opção: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    // Consulta dados de um determinado livro
                    utilizador.consultarLivro(1);
                    break;
                case 2:

                    // Consulta dados de todos os livros
                    utilizador.consultarTodosLivros();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }scanner.close();

        } while (op != 4);
    }

    static void exibirMenuBibliotecario() {
        System.out.println("Menu (sendo bibliotecario):");
        System.out.println("1. Opção 1");
        System.out.println("2. Opção 2");
        System.out.println("3. Opção 3");
        System.out.println("4. Sair");
    }

    public void percorrerMenuBibliotecario() {
        do {
            exibirMenuBibliotecario();
            System.out.print("op uma opção: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    // Consulta dados dos livros
                    bibliotecario.consultarTodosLivros();
                    break;
                case 2:
                    // Consulta dados de um determinado livro
                    bibliotecario.consultarLivro(1);
                    break;
                case 3:

                    // Inserindo o livro no banco de dados
                    bibliotecario.inserirLivro(livroComDados);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            scanner.close();
        } while (op != 4);
    }
}