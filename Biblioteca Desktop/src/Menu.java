import java.util.Scanner;

public class Menu {
    Scanner scan = new Scanner(System.in);

    Utilizador utilizador = new Utilizador();
    Bibliotecario bibliotecario;

    int idLivro;
    String isbn;
    String titulo;
    String autor;
    String editora;
    int ano_Publi;
    String genero;

    boolean disponibilidade;

    // Criando um objeto Livro
    //Livro livroComDados = new Livro(2, "1234567890123", "Java for Beginners", "John Doe", "Tech Publishing", 2022, "Programming", true);
    Livro livroComDados = new Livro(2, "1234567890123", "Java for Beginners", "John Doe", "Tech Publishing", 2022, "Programming", true);

    int op;

    public static void exibirMenuUtilizador() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Menu (sendo utilizador):");
        System.out.println("1. Consultar dados de um determinado livro");
        System.out.println("2. Consultar dados de todos os livros");
        System.out.println("3. Sair");
        System.out.println("-------------------------------------------------");
    }

    public void percorrerMenuUtilizador() {
        do {
            exibirMenuUtilizador();
            try {
                System.out.print("Escolha uma das opções: ");
                op = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scan.nextLine(); // Limpa o buffer do scan
                op = 0; // Atribui um valor padrão para evitar loop infinito
            }

            switch (op) {
                case 1:
                    // Consulta dados de um determinado livro
                    try {
                        System.out.println("Inserir o id do livro, para saber mais:");
                        idLivro = scan.nextInt();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    utilizador.consultarLivro(idLivro);
                    break;
                case 2:
                    // Consulta dados de todos os livros
                    utilizador.consultarTodosLivros();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 3);
    }

    static void exibirMenuBibliotecario() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Menu (sendo bibliotecario):");
        System.out.println("1. Consultar dados de todos os livros");
        System.out.println("2. Consultar dados de um determinado livro");
        System.out.println("3. Inserir um novo livro");
        System.out.println("4. Eliminar um determinado livro");
        System.out.println("5. Sair");
        System.out.println("-------------------------------------------------");
    }

    public void percorrerMenuBibliotecario() {
        do {
            exibirMenuBibliotecario();
            try {
                System.out.print("Escolha uma das opções: ");
                op = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scan.nextLine(); // Limpa o buffer do scan
                op = 0; // Atribui um valor padrão para evitar loop infinito
            }

            switch (op) {
                case 1: // Consulta dados de todos os livros
                    bibliotecario.consultarTodosLivros();
                    break;
                case 2: // Consulta dados de um determinado livro
                    try {
                        System.out.println("Inserir o id do livro, para saber mais:");
                        idLivro = scan.nextInt();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    bibliotecario.consultarLivro(idLivro);
                    break;
                case 3: // Inserindo o livro na base de dados
                    try {
                        System.out.println("Insira o id do livro");
                        idLivro = scan.nextInt();
                        System.out.println("Insera o isbn");
                        isbn = scan.nextLine();
                        System.out.println("Insera o titulo");
                        titulo = scan.nextLine();
                        System.out.println("Insera o autor");
                        autor = scan.nextLine();
                        System.out.println("Insera a editora");
                        editora = scan.nextLine();
                        System.out.println("Insera o ano da publicacão:");
                        ano_Publi = scan.nextInt();
                        System.out.println("Insera o genero");
                        genero = scan.nextLine();
                        System.out.println("Insera a disponibilidade");
                        disponibilidade = scan.nextBoolean();
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    Livro livroComDados = new Livro(idLivro, isbn, titulo, autor, editora, ano_Publi, genero, disponibilidade);

                    bibliotecario.inserirLivro(livroComDados);
                    break;
                case 4: // Eliminar livro da base de dados
                    try {
                        System.out.println("Inserir o id do livro, para eliminar da base de dados");
                        idLivro = scan.nextInt();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    bibliotecario.eliminarLivro(idLivro);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            //scan.close();
        } while (op != 4);
    }
}